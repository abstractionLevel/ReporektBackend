package com.example.ReportPlayer.controller.repository.report.description;

import com.example.ReportPlayer.builder.comment.CommentBuilder;
import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.DescriptionReportRepositoryFactory;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.comment.CommentBrazil;
import com.example.ReportPlayer.models.report.description.DescriptionReportBrazil;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.repository.comment.CommentBaseRepository;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DescriptionReportRepositoryTest {


    private DescriptionReportBaseRepository repository;
    private CommentBaseRepository commentBaseRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ApplicationContext context;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void save_ShouldSave(String region) throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final User userSaved = entityManager.persistAndFlush(user);
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().player(playerSaved).description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(userSaved).build();
        //act
        repository = DescriptionReportRepositoryFactory.getRepository(region,context);
        final DescriptionReport descriptionReportExpected = (DescriptionReport) repository.save(descriptionReport);

        //assert
        assertEquals("Apollo1",descriptionReportExpected.getPlayer().getNickname());
        assertEquals("it was afk",descriptionReportExpected.getDescription());
        assertEquals(ReportType.AFK.toString(),descriptionReportExpected.getReportType());
    }


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void delete_ShouldDelete(String region) throws Exception {
        //arrange
        repository = DescriptionReportRepositoryFactory.getRepository(region,context);
        final User user = entityManager.persistAndFlush(UserBuilder.newBuilder().username("APollo12").email("apollo@gmail.com").password("Tricolore33").
                confirmPassword("Tricolore33").isActive(true).build());
        final Player player =  entityManager.persistAndFlush(PlayerBuilder.newBuilder().username("Apollo1").region(region).build());
        final DescriptionReport descriptionReport = entityManager.persistAndFlush( DescriptionReportBuilder.newBuilder().description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(user).player(player).build());
        //act
        repository.deleteById(descriptionReport.getId());
        final Optional<DescriptionReport> descriptionReportExpected = repository.findById(descriptionReport.getId());
        //assert
        assertFalse(descriptionReportExpected.isPresent());

    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldFetchById(String region) throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").email("apollo@gmail.com").password("Tricolore33").
                confirmPassword("Tricolore33").isActive(true).build();
        entityManager.persist(user);
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(region).build();
        final Player playerSaved = entityManager.persistAndFlush(player);
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(user).build();
        descriptionReport.setPlayer(playerSaved);
        final DescriptionReport descriptionReportSaved = entityManager.persistAndFlush(descriptionReport);
        //act
        repository = DescriptionReportRepositoryFactory.getRepository(region,context);
        final Optional<DescriptionReport> descriptionReportExpected =  repository.findById(descriptionReportSaved.getId());
        //assert
        if(descriptionReportExpected.isPresent()) {
            assertEquals("it was afk",descriptionReportExpected.get().getDescription());
            assertEquals(user,descriptionReportExpected.get().getUser());
            assertEquals(player,descriptionReportExpected.get().getPlayer());
            assertEquals(ReportType.AFK.toString(),descriptionReportExpected.get().getReportType());
        }

    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void delete_WhenDeleteDescriptionReportShouldDeleteAlsoComment(String region) throws Exception {
        //arrange
        repository = DescriptionReportRepositoryFactory.getRepository(region,context);
        commentBaseRepository = (CommentBaseRepository) context.getBean("comment_repository_"+region);
        final User user = entityManager.persistAndFlush(UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build());
        final Player playerSaved = entityManager.persistAndFlush(PlayerBuilder.newBuilder().username("Apollo12").region(region).build());
        final DescriptionReport descriptionReport = entityManager.persistAndFlush(DescriptionReportBuilder.newBuilder().player(playerSaved).description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(user).build());
        entityManager.persistAndFlush(CommentBuilder.newBuilder().comment("he was afk").region(region).user(user).report(descriptionReport).build());
        //act
        repository.delete(descriptionReport);
        final Optional<DescriptionReport> descriptionReportExpected = repository.findById(descriptionReport.getId());
        final Comment commentExpected = (Comment) commentBaseRepository.findByDescriptionReport(descriptionReport);
        //assert
        System.out.println(commentExpected);
        assertFalse(descriptionReportExpected.isPresent());
        assertNull(commentExpected);

    }

    @Test
    public void delete_WhenDeleteDescriptionReportShouldDeleteAlsoCommentBrazil() throws Exception {
        //arrange
        repository = (DescriptionReportBaseRepository) context.getBean("description_report_repository_"+ Server.Region.BRAZIL);
        commentBaseRepository = (CommentBaseRepository) context.getBean("comment_repository_"+ Server.Region.BRAZIL);
        final User user = entityManager.persistAndFlush(UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build());

        final Player playerSaved = (Player) entityManager.persistAndFlush(PlayerBuilder.newBuilder().username("Apollo12").region(Server.Region.BRAZIL).build());

        final DescriptionReportBrazil descriptionReport = (DescriptionReportBrazil) DescriptionReportBuilder.newBuilder().player(playerSaved).description("it was afk").
                reportType(ReportType.AFK.toString()).region(Server.Region.BRAZIL).user(user).build();
        descriptionReport.getComment().add((CommentBrazil) CommentBuilder.newBuilder().user(user).region(Server.Region.BRAZIL).report(descriptionReport).comment("afk").build());
        entityManager.persistAndFlush(descriptionReport);
        //act
        repository.deleteById(descriptionReport.getId());
        final CommentBrazil commentBrazil = (CommentBrazil) commentBaseRepository.findByComment("afk");
        //assert
        assertNull(commentBrazil);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }

}
