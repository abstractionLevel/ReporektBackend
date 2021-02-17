package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.comment.CommentFactory;
import com.example.ReportPlayer.models.comment.Comment;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class CommentFactoryTest {


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldReturnInstanceObjForEveryRegion(String region) throws Exception {
        //arrange
        //act
        final Comment comment = CommentFactory.getComment(region);
        //assert
        assertNotNull(comment);
    }

    //non-api
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
