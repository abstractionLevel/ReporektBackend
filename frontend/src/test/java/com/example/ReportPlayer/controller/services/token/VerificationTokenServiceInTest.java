package com.example.ReportPlayer.controller.services.token;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.user.UserRepositoryFactory;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.services.token.VerificationTokenServiceImpl;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;


@SpringBootTest
public class VerificationTokenServiceInTest {

    @Autowired
    private VerificationTokenServiceImpl service;

    private UserBaseRepository userBaseRepository;
    @Autowired
    private ApplicationContext applicationContext;


    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void save_ShouldSave(String region)  throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo1").
                password("Apo3llo1212").confirmPassword("Apo3llo1212").email("apol33lo1@gmail.com").isActive(false).build();
        //act
        userBaseRepository = UserRepositoryFactory.getRepository(region,applicationContext);
        final User userSaved = (User) userBaseRepository.save(user);
        //assert
        final VerificationToken tokenExpected =  service.save(userSaved);
    }

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void find_ShouldFind(String region) throws Exception {
        //arrange
        //act
        userBaseRepository = UserRepositoryFactory.getRepository(region,applicationContext);
        final User user = (User) userBaseRepository.findByUsername("Apollo");
        final VerificationToken verificationToken = service.findTokenByUser(user);
        //assert
        assertNotNull(verificationToken);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
