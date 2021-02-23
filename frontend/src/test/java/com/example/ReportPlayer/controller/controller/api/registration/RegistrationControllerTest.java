package com.example.ReportPlayer.controller.controller.api.registration;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.UserAlreadyExistException;
import com.example.ReportPlayer.services.token.VerificationTokenServiceImpl;
import com.example.ReportPlayer.builder.token.VerificationTokenBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.builder.user.UserDtoBuilder;
import com.example.ReportPlayer.services.user.UserServiceImpl;
import com.example.ReportPlayer.utils.pojo.Email;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.services.email.EmailSenderService;
import com.example.ReportPlayer.utils.VerifyToken;
import com.example.ReportPlayer.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;


    @MockBean
    private VerificationTokenServiceImpl verificationTokenService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private EmailSenderService emailSenderService;


    @Autowired
    private MessageSource messageSource;

    @MockBean
    private VerifyToken verifyToken;


    @Test
    public void save_ShouldRegisterUser() throws Exception {
        //arrange
        final UserDto userDto = UserDtoBuilder.newBuilder().username("Apollo11").
                password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").build();
        final User user = UserBuilder.newBuilder().username(userDto.getUsername()).
                email(userDto.getEmail()).password(userDto.getPassword()).confirmPassword(userDto.getConfirmPassword()).isActive(false).build();
        final VerificationToken token = VerificationTokenBuilder.newBuilder().user(user).region(Server.Region.EUW).build();
        //act
        when(userService.save(any(UserDto.class))).thenReturn(user);
        when(verificationTokenService.save(any(User.class))).thenReturn(token);
        mvc.perform(post("/api/public/v1/registration/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());
        //assert
        verify(userService,times(1)).save(any(UserDto.class));
        verify(verificationTokenService,times(1)).save(any(User.class));
        verify(emailSenderService,times(1)).send(any(Email.class));
    }

    @Test
    public void save_ShouldThrowErrorWhenUserIsAlreadyExist() throws Exception {
        //arrange
        //act
        when(userService.save(any(UserDto.class))).thenThrow(UserAlreadyExistException.class);
        mvc.perform(post("/api/public/v1/registration/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDtoBuilder.newBuilder().username("Apollo11").
                        password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").build())))
                .andExpect(status().isConflict())
                .andDo(print())
                .andReturn();
        //assert
    }

    @Test
    public void save_ShouldInvalidRegistrationIfPasswordsNotMatch1() throws Exception {
        //arrange
        //act
        mvc.perform(post("/api/public/v1/registration/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDtoBuilder.newBuilder().username("Apollo11").
                        password("Apollo1010").confirmPassword("Apollo101").email("apollo@gmail.com").build())))
                .andExpect(status().isBadRequest())
                .andDo(print());
        //assert
    }

    @Test
    public void save_ShouldInvalidRegistrationIfSomeFieldIsNull() throws Exception {
        //arrange
        //act
        mvc.perform(post("/api/public/v1/registration/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDtoBuilder.newBuilder().username("").
                        password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").build())))
                .andExpect(status().isBadRequest())
                .andDo(print());
        //assert
    }


    @Test
    public void confirm_ValidToken() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo").
                password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken verificationToken = VerificationTokenBuilder.newBuilder().region(Server.Region.EUW).user(user).build();
        //act
        when(userService.activateUser(any(User.class))).thenReturn(user);
        when(verificationTokenService.findTokenByConfirmationToken(anyString())).thenReturn(verificationToken);
        doNothing().when(verificationTokenService).deleteTokenByToken(any(VerificationToken.class));
        when(verifyToken.verify(any(VerificationToken.class))).thenReturn("valid");
        mvc.perform(get("/api/public/v1/registration/?token="+verificationToken.getConfirmationToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //assert
        verify(userService,times(1)).activateUser(any(User.class));
        verify(verificationTokenService,times(1)).findTokenByConfirmationToken(anyString());
        verify(verificationTokenService,times(1)).deleteTokenByToken(any(VerificationToken.class));
    }


    @Test
    public void confirm_ExpiredToken() throws Exception {
        //arrange
        String token = "token";
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken verificationToken = VerificationTokenBuilder.newBuilder().region(Server.Region.EUW).user(user).build();
        //act
        when(verificationTokenService.findTokenByConfirmationToken(anyString())).thenReturn(verificationToken);
        when(verifyToken.verify(any(VerificationToken.class))).thenReturn("expired");
        doNothing().when(userService).delete(any(User.class));
         mvc.perform(put("/api/public/v1/registration/")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(verificationToken.getConfirmationToken()))
                 .andExpect(status().is4xxClientError())
                 .andDo(print())
                 .andReturn();
        //assert
        verify(userService,times(1)).delete(any(User.class));
        verify(verificationTokenService,times(1)).findTokenByConfirmationToken(anyString());
    }

}
