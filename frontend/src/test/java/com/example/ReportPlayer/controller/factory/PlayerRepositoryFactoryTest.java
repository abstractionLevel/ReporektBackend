package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.PlayerRepositoryFactory;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class PlayerRepositoryFactoryTest {

    @Autowired
    private ApplicationContext context;


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldReturnBean(String region) throws Exception {
        //arrange
        //act
        final PlayerBaseRepository baseRepository = PlayerRepositoryFactory.getRepository(region,context);
        //assert
        assertNotNull(baseRepository);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
