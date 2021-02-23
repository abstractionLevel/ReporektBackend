package com.example.ReportPlayer.controller.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.ReportTypeRepositoryFactory;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ReportTypeRepositoryTest  {


    private ReportTypeBaseRepository repository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ApplicationContext applicationContext;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void save_ShouldSaveReportType(String region) throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                count(0).region(region).build();
        reportType.setPlayer(playerSaved);
        //act
        repository = ReportTypeRepositoryFactory.getRepository(region,applicationContext);
        final ReportType reportTypeExpected = (ReportType) repository.save(reportType);
        //assert
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.getReportType());
    }


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldFindByReportType(String region) {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(region).build();
        reportType.setPlayer(playerSaved);
        entityManager.persistAndFlush(reportType);
        //act
        repository = ReportTypeRepositoryFactory.getRepository(region,applicationContext);
        final ReportType reportTypeExpected = (ReportType) repository.findByReportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString());
        //assert
        assertNotNull(reportTypeExpected);
    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldFindByReportTypeAnaIdPlayer(String region) {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(region).build();
        reportType.setPlayer(playerSaved);
        entityManager.persistAndFlush(reportType);
        //act
        repository = ReportTypeRepositoryFactory.getRepository(region,applicationContext);
        final ReportType reportTypeExpected = (ReportType) repository.findByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId());
        //assert
        assertNotNull(reportTypeExpected);
    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldReturnNullIfReportTypeNotExist(String region) {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(region).build();
        reportType.setPlayer(playerSaved);
        entityManager.persistAndFlush(reportType);
        //act
        repository = ReportTypeRepositoryFactory.getRepository(region,applicationContext);
        final ReportType reportTypeExpected = (ReportType) repository.findByReportType(com.example.ReportPlayer.enumerated.ReportType.CHEATING.toString());
        //assert
        assertNull(reportTypeExpected);
    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void delete_ShouldDeleteByIdPlayer(String region) throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(region).build();
        reportType.setPlayer(playerSaved);
        entityManager.persistAndFlush(reportType);
        //act
        repository = ReportTypeRepositoryFactory.getRepository(region,applicationContext);
        repository.deleteByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId());

    }

    @Test
    public void find_ShouldGetAllReportTypeByPlayer() throws Exception {
        //arrange
        repository = (ReportTypeBaseRepository) applicationContext.getBean("report_type_repository_"+Server.Region.EUW);
        final Player player = entityManager.persistAndFlush(PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).build());

        final List<ReportType> reportTypes = new ArrayList<>();
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(10).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).build());
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(1).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.CHEATING.toString()).build());
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(3).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.NEGATIVE_ATTITUDE.toString()).build());
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(4).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.HATE_SPEECH.toString()).build());
        //act
        repository.saveAll(reportTypes);
        final List<ReportType> reportTypesExpected = repository.findAllReportTypeByPlayerId(player.getId());
        //assert
        assertNotNull(reportTypesExpected);
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypesExpected.get(0).getReportType());
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.CHEATING.toString(),reportTypesExpected.get(1).getReportType());
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.NEGATIVE_ATTITUDE.toString(),reportTypesExpected.get(2).getReportType());
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.HATE_SPEECH.toString(),reportTypesExpected.get(3).getReportType());

    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
