package com.example.ReportPlayer.controller.services.token;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.token.VerificationTokenBaseRepository;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.services.token.VerificationTokenServiceImpl;
import com.example.ReportPlayer.services.user.UserService;
import com.example.ReportPlayer.builder.token.VerificationTokenBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VerificationTokenServiceTest {


    @InjectMocks
    private VerificationTokenServiceImpl tokenService;
    @Mock
    private UserService userService;
    @Mock
    private VerificationTokenBaseRepository verificationTokenRepository;
    @Mock
    private UserBaseRepository userRepository;


    @Test
    public void save_shouldSaveForEveryRegion()  throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(Server.Region.EUW).build();
        //act
        when(verificationTokenRepository.save(any(VerificationToken.class))).thenReturn(token);
        VerificationToken tokenExpected =  tokenService.save(user);
        //assert
        verify(verificationTokenRepository,times(1)).save(any(VerificationToken.class));
        assertEquals(token.getConfirmationToken(),tokenExpected.getConfirmationToken());
    }


    @Test
    public void shouldDelete()  throws Exception{
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(Server.Region.EUW).build();
        //act
        doNothing().when(verificationTokenRepository).delete(any(VerificationToken.class));
        tokenService.deleteTokenByToken(token);
        //assert
    }

    @Test
    public void shouldFindByUser() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(Server.Region.EUW).build();
        //act
        when(verificationTokenRepository.findTokenByUser(any(User.class))).thenReturn(token);
        final VerificationToken tokenExpected = tokenService.findTokenByUser(user);
        //assert
        assertEquals(token.getConfirmationToken(),tokenExpected.getConfirmationToken());

    }

    @Test
    public void setRepository() throws Exception {
        //arrange
        //act
      //  when(VerificationTokenRepositoryFactory.getRepository(region,applicationContext)).thenReturn(verificationTokenRepository);
        //tokenService.setRepositoryRegion(region);
        //assert
    }

    @Test
    public void find_shouldFindByConfirmationToken() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(Server.Region.EUW).build();
        //act
        when(verificationTokenRepository.findTokenByConfirmationToken(any(String.class))).thenReturn(token);
        //assert
        VerificationToken expect = tokenService.findTokenByConfirmationToken(token.getConfirmationToken());
        assertEquals(expect.getConfirmationToken(),token.getConfirmationToken());
        verify(verificationTokenRepository,times(1)).findTokenByConfirmationToken(any(String.class));
    }

    @Test
    public void shouldFindByUserNotExist() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").
                password("Apollo1212").confirmPassword("Apollo1212").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(Server.Region.EUW).build();
        //act
        when(verificationTokenRepository.findTokenByUser(any(User.class))).thenReturn(null);
        VerificationToken tokenExpected = tokenService.findTokenByUser(user);
        //assert
        assertNull(tokenExpected);
    }





}
