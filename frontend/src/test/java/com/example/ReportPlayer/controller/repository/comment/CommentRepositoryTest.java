package com.example.ReportPlayer.controller.repository.comment;

import com.example.ReportPlayer.builder.comment.CommentBuilder;
import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.comment.CommentBaseRepository;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
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
public class CommentRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ApplicationContext context;
    private CommentBaseRepository repository;
    private UserBaseRepository userBaseRepository;
    private PlayerBaseRepository playerBaseRepository;
    private DescriptionReportBaseRepository descriptionReportBaseRepository;



    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void save_ShouldSave(String region) throws Exception {
        //arrange

        repository = (CommentBaseRepository) context.getBean("comment_repository_"+region);
        final User user =  entityManager.persistFlushFind(UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build());
        final Player playerSaved =entityManager.persistFlushFind(PlayerBuilder.newBuilder().username("Apollo12").region(region).build());
        final DescriptionReport descriptionReport = entityManager.persistFlushFind(DescriptionReportBuilder.newBuilder().player(playerSaved).description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(user).build());
        final Comment comment = CommentBuilder.newBuilder().user(user).report(descriptionReport).region(region).
                comment("he was afk").build();
        //act
        final Comment commentSaved = (Comment) repository.save(comment);
        //assert
        assertEquals("he was afk",commentSaved.getComment());

    }



    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find(String region) throws Exception {
        //arrange
        repository = (CommentBaseRepository) context.getBean("comment_repository_"+region);
        final User user = entityManager.persistFlushFind(UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build());
        final Player playerSaved = entityManager.persistFlushFind(PlayerBuilder.newBuilder().username("Apollo12").region(region).build());
        final DescriptionReport descriptionReport = entityManager.persistFlushFind(DescriptionReportBuilder.newBuilder().player(playerSaved).description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(user).build());
        final Comment comment = entityManager.persistFlushFind(CommentBuilder.newBuilder().comment("he was afk").region(region).user(user).report(descriptionReport).build());
        //act
        //assert
        final Optional<Comment> commentExpected = repository.findById(comment.getId());
        assertEquals("he was afk", commentExpected.get().getComment());
        assertNotNull(commentExpected.get().getUser());
        assertNotNull(commentExpected.get().getDescriptionReport());

    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void delete_ShouldDeleteById(String region) throws Exception {
        //arrange
        repository = (CommentBaseRepository) context.getBean("comment_repository_"+region);
        final User user = entityManager.persistAndFlush(UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build());
        final Player playerSaved = entityManager.persistAndFlush(PlayerBuilder.newBuilder().username("Apollo12").region(region).build());
        final DescriptionReport descriptionReport = entityManager.persistAndFlush(DescriptionReportBuilder.newBuilder().player(playerSaved).description("it was afk").
                reportType(ReportType.AFK.toString()).region(region).user(user).build());
        final Comment comment = entityManager.persistAndFlush(CommentBuilder.newBuilder().comment("he was afk").region(region).user(user).report(descriptionReport).build());
        //act
        repository.deleteById(comment.getId());
        final Optional<Comment> commentExpected  = repository.findById(comment.getId());
        //assert
        assertFalse(commentExpected.isPresent());

    }


    //non-api
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
