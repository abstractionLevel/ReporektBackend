package com.example.ReportPlayer.controller.config;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.services.report.player.PlayerService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class PlayerServiceImplConfigTest {

    @Autowired
    private ApplicationContext context;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldCheckIfBeanExist(String region) throws Exception {
        //arrange
        //act
        final PlayerService playerService = (PlayerService) context.getBean("player_service_"+region);
        //assert
        assertNotNull(playerService);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
