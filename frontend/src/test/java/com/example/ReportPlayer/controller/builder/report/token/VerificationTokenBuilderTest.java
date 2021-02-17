package com.example.ReportPlayer.controller.builder.report.token;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.builder.token.VerificationTokenBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class VerificationTokenBuilderTest {

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void token_ShouldCreateVerificationTokenObject(String region) throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(region).build();
        //act
        //assert
        System.out.println(token.getConfirmationToken());
        System.out.println(token.getExpiryDate());
        assertNotNull(token.getExpiryDate());
        assertNotNull(token.getUser());
        assertNotNull(token.getIdToken());
        assertNotNull(token.getConfirmationToken());
        assertEquals("Apollo12",token.getUser().getUsername());
        assertEquals("Apollo1212",token.getUser().getPassword());
        assertEquals("Apollo1212",token.getUser().getConfirmPassword());

    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
