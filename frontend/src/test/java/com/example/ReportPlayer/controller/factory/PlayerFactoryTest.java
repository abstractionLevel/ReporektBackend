package com.example.ReportPlayer.controller.factory;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.PlayerFactory;
import com.example.ReportPlayer.models.report.player.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PlayerFactoryTest {

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void getObj_ShouldReturnObj(String region) throws Exception {
        //arrange
        //act
        Player player = PlayerFactory.getPlayer(region);
        //assert
        assertTrue(player instanceof  Player);
    }


    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
