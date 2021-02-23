package com.example.ReportPlayer.controller.repository.report.Player;

import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.PlayerRepositoryFactory;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportBrazil;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerBrazil;
import com.example.ReportPlayer.models.report.type.ReportTypeBrazil;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner.class)
public class PlayerRepositoryTest {

    private PlayerBaseRepository repository;
    private ReportTypeBaseRepository reportTypeBaseRepository;
    private DescriptionReportBaseRepository descriptionReportBaseRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ApplicationContext applicationContext;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void save_ShouldSave(String region) throws Exception {
        //arrange
        final Player player =  PlayerBuilder.newBuilder().username("Apollo12").region(region).id(1L).build();
        //act
        repository = PlayerRepositoryFactory.getRepository(region,applicationContext);
        final Player playerExpected = (Player) repository.save(player);
        //assert
        assertEquals("Apollo12",playerExpected.getNickname());
    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldFinByUsername(String region) throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo12").region(region).id(1L).build();
        //act
        testEntityManager.persistFlushFind(player);
        repository = PlayerRepositoryFactory.getRepository(region,applicationContext);
        Player playerExpected = (Player) repository.findByNickname("Apollo12");
        //assert
        System.out.println(playerExpected.getNickname());
        assertEquals("Apollo12",playerExpected.getNickname());

    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldFindByGetOne(String region) throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo12").region(region).id(1L).build();
        //act
        //act
        final Player playerSaved = testEntityManager.persistFlushFind(player);
        repository = PlayerRepositoryFactory.getRepository(region,applicationContext);
        final Player playerExpected = (Player) repository.getOne(playerSaved.getId());
        //assert
        assertEquals("Apollo12",playerExpected.getNickname());

    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void delete_ShouldDeleteById(String region) throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo12").region(region).id(1L).build();
        //act
        final Player playerSaved  = testEntityManager.persistFlushFind(player);
        repository = PlayerRepositoryFactory.getRepository(region,applicationContext);
        System.out.println(playerSaved.getId());
        repository.deleteById(playerSaved.getId());

        final Optional playerExpected = repository.findById(playerSaved.getId());
        //assert
        assertEquals(false,playerExpected.isPresent());
    }

    @Test
    public void delete_ShouldClearCascade() throws Exception {
        //arrange
        repository = (PlayerBaseRepository) applicationContext.getBean("player_repository_"+ Server.Region.BRAZIL);
        descriptionReportBaseRepository = (DescriptionReportBaseRepository) applicationContext.getBean("description_report_repository_"+ Server.Region.BRAZIL);
        reportTypeBaseRepository = (ReportTypeBaseRepository) applicationContext.getBean("report_type_repository_"+ Server.Region.BRAZIL);
        final User user = testEntityManager.persistAndFlush(UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build());
        final PlayerBrazil player = (PlayerBrazil) PlayerBuilder.newBuilder().region(Server.Region.BRAZIL).username("Apollo").build();
        final ReportTypeBrazil reportType = (ReportTypeBrazil) ReportTypeBuilder.newBuilder().reportType(ReportType.AFK.toString()).player(player).
                count(0).region(Server.Region.BRAZIL).build();
        final DescriptionReportBrazil descriptionReportBrazil = (DescriptionReportBrazil) DescriptionReportBuilder.newBuilder().description("He was afk").reportType(ReportType.AFK.toString()).player(player)
                .user(user).region(Server.Region.BRAZIL).build();
        player.getDescriptionReport().add(descriptionReportBrazil);
        player.getReportType().add(reportType);
        //act
        final PlayerBrazil playerBrazilSaved = testEntityManager.persistAndFlush(player);
        repository.delete(playerBrazilSaved);
        final Optional<PlayerBrazil> playerBrazilExpected = repository.findById(playerBrazilSaved.getId());
        final DescriptionReport descriptionReportExpected = (DescriptionReport) descriptionReportBaseRepository.findByDescription("He was afk");
        final ReportTypeBrazil reportTypeExpected = (ReportTypeBrazil) reportTypeBaseRepository.findReportTypeByPlayer(player);
        //assert
        assertNull(descriptionReportExpected);
        assertNull(reportTypeExpected);
        assertFalse(playerBrazilExpected.isPresent());

    }

    @Test
    public void find_ShouldReturnPlayerOrderByDesc() throws Exception {
        //arrange
        final List<Player> playerList = new ArrayList<>();
        playerList.add(PlayerBuilder.newBuilder().reportCount(200).username("Michael").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("Chill").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(1500).username("Apollo").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(10).username("Brain").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(400).username("Frank").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(3).username("George").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(22).username("Hercules").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(44).username("Heat").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(2).username("Sprint").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(1).username("Spider Man").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(3).username("Venom").region(Server.Region.EUW).build());

        //act
        repository = (PlayerBaseRepository) applicationContext.getBean("player_repository_"+ Server.Region.EUW);
        repository.saveAll(playerList);
        List<Player> playersExpected = repository.findTop5ByOrderByReportCountDesc();
        //assert
        assertEquals("Apollo",playersExpected.get(0).getNickname());
        assertEquals("Frank",playersExpected.get(1).getNickname());
        assertEquals("Michael",playersExpected.get(2).getNickname());
        assertEquals("Chill",playersExpected.get(3).getNickname());
        assertEquals("Heat",playersExpected.get(4).getNickname());
    }

    @Test
    public void find_ShouldReturnLastReportedByDate() throws Exception {
        //arrange
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date = sdf.parse("31-08-1952 10:20:56");

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date1 = sdf1.parse("31-08-1942 10:20:56");

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date2 = sdf2.parse("31-08-1932 10:20:56");

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date3 = sdf3.parse("31-08-1922 10:20:56");

        SimpleDateFormat sdf4 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date4 = sdf4.parse("31-08-1912 10:20:56");

        SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date5 = sdf5.parse("31-08-1902 10:20:56");


        List<Player> playerList = new  ArrayList<>();
        playerList.add(PlayerBuilder.newBuilder().reportCount(1500).username("Apollo").region(Server.Region.EUW).date(date1).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(200).username("Michael").region(Server.Region.EUW).date(date).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("George").region(Server.Region.EUW).date(date3).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(10).username("Hercules").region(Server.Region.EUW).date(date4).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("Marvel").region(Server.Region.EUW).date(date2).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(400).username("Pippo").region(Server.Region.EUW).date(date5).build());
        //act
        repository = (PlayerBaseRepository) applicationContext.getBean("player_repository_"+ Server.Region.EUW);
        repository.saveAll(playerList);
        List<Player> playersExpected = repository.findTop3ByOrderByDateDesc();
        //assert
        for(Player player: playersExpected) {
            System.out.println(player.getDate());
        }
        assertNotNull(playersExpected);
    }
    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
