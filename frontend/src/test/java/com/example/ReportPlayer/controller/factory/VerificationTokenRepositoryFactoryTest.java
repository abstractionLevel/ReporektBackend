package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.token.VerificationTokenRepositoryFactory;
import com.example.ReportPlayer.repository.token.VerificationTokenBaseRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class VerificationTokenRepositoryFactoryTest {

    @Autowired
    private ApplicationContext applicationContext;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void repository_ShouldReturnAllRepository(String region) throws Exception {
        //arrange
        //act
        final VerificationTokenBaseRepository verificationToken = VerificationTokenRepositoryFactory.getRepository(region,applicationContext);
        //assert
        assertNotNull(verificationToken);
    }

    //non-api
    public static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}

