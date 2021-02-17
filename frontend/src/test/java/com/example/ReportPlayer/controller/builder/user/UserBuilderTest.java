package com.example.ReportPlayer.controller.builder.user;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class UserBuilderTest {


    @ParameterizedTest
    @MethodSource("streamOfRegion")
    public void user_ShouldBuildingObjectForEveryRegion(String region) throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo3312329").
                password("Apollo3312329").confirmPassword("Apollo3312329").email("Apollo121").email("Apollo@gmail.com").build();
        //act
        //assert
        System.out.println(user);
        assertEquals("Apollo3312329",user.getUsername());
        assertEquals("Apollo3312329",user.getPassword());
        assertEquals("Apollo3312329",user.getConfirmPassword());
        assertEquals("Apollo@gmail.com",user.getEmail());

    }

    //not-test
    private static Stream streamOfRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
