package com.example.ReportPlayer.controller.config;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.services.user.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class MyUserDetailServiceImplTest {


    @Autowired
    private ApplicationContext context;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldCheckIfBeanExist(String region) throws Exception {
        //assert
        //act
        final UserDetailServiceImpl userDetailServiceExpeted = (UserDetailServiceImpl) context.getBean("user_detail_service_"+region);
        //arrange
        assertNotNull(userDetailServiceExpeted);
    }

    private Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
