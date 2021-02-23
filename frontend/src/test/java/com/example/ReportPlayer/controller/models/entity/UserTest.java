package com.example.ReportPlayer.controller.models.entity;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserTest {


    @Test
    public void testAttributeUser() throws Exception {
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();

        assertEquals(user.getPassword(), user.getPassword());
        assertEquals(user.getEmail(), user.getEmail());
        assertEquals(user.getUsername(), user.getUsername());



    }


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void getAllEntity(String region) throws Exception {


    }


    //non-api
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }

}
