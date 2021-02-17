package com.example.ReportPlayer.controller.repository.token;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.token.VerificationTokenBaseRepository;
import com.example.ReportPlayer.builder.token.VerificationTokenBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class VerificationTokenRepositoryTest {

    @Autowired
    private VerificationTokenBaseRepository verificationTokenRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void save_shouldSaveToken() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final User userSaved = entityManager.persistAndFlush(user);
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(userSaved).region(Server.Region.EUW).build();
        //act
        VerificationToken savedToken =  verificationTokenRepository.save(token);
        //assert
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(savedToken.getUser());
        assertNotNull(savedToken);
    }



    @Test
    public void find_shouldFindToken() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final User userSaved = entityManager.persistAndFlush(user);
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(userSaved).region(Server.Region.EUW).build();
        final VerificationToken tokenSaved = entityManager.persistAndFlush(token);
        //act
        final VerificationToken expectToken =  verificationTokenRepository.findTokenByConfirmationToken(token.getConfirmationToken());
        //assert
        assertNotNull(expectToken);
        assertEquals(token.getConfirmationToken(),expectToken.getConfirmationToken());
    }

    @Test
    public void find_shouldFindTokenByUser() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
        password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final User userSaved = entityManager.persistAndFlush(user);
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(userSaved).region(Server.Region.EUW).build();
        final VerificationToken tokenSaved = entityManager.persistAndFlush(token);
        //act
        VerificationToken tokenExpected =  verificationTokenRepository.findTokenByUser(tokenSaved.getUser());

        //assert
        assertEquals("Apollo12",tokenExpected.getUser().getUsername());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(tokenExpected.getUser().getUsername());
    }


    @Test
    public void delete_shouldDeleteTokenByUser() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final User userSaved = entityManager.persistAndFlush(user);
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(userSaved).region(Server.Region.EUW).build();
        final VerificationToken tokenSaved = entityManager.persistAndFlush(token);
        //act
        verificationTokenRepository.delete(tokenSaved);
        final VerificationToken verificationTokenExpected =  verificationTokenRepository.findTokenByConfirmationToken(tokenSaved.getConfirmationToken());
        //assert
        assertNull(verificationTokenExpected);
    }

    @Test
    public void delete_shouldDeleteByToken() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final User userSaved = entityManager.persistAndFlush(user);
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(userSaved).region(Server.Region.EUW).build();
        final VerificationToken tokenSaved = entityManager.persistAndFlush(token);
        //act
        verificationTokenRepository.delete(tokenSaved);
        final VerificationToken verificationTokenExpected = verificationTokenRepository.findTokenByConfirmationToken(tokenSaved.getConfirmationToken());
        //assert
        assertNull(verificationTokenExpected);
    }




}
