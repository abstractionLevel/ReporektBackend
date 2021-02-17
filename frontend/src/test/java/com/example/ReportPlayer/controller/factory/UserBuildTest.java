package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserBuildTest {



    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void user_ShouldCreateObjectUserForEveryRegion(String region) {
        //act
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        //act
        //assert
        System.out.println(user);
        assertEquals("Apollo12",user.getUsername());
        assertEquals("Apollo1212",user.getPassword());
        assertEquals("Apollo1212",user.getConfirmPassword());
        assertEquals("apollo@gmail.com",user.getEmail());
    }
    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
