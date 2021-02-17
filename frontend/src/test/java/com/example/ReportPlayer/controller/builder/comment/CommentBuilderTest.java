package com.example.ReportPlayer.controller.builder.comment;

import com.example.ReportPlayer.builder.comment.CommentBuilder;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuw;
import com.example.ReportPlayer.models.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class CommentBuilderTest {

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldCheckIfFieldsExist(String region) throws Exception {
        //arrange
        final Comment comment = CommentBuilder.newBuilder().comment("he was afk").report(new DescriptionReport()).user(new User()).region(region).build();
        //act
        //assert
        assertEquals("he was afk",comment.getComment());
        assertNotNull(comment.getDescriptionReport());
        assertNotNull(comment.getUser());
    }

    //non-api
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
