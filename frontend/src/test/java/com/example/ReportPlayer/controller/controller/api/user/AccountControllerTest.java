package com.example.ReportPlayer.controller.controller.api.user;

import com.example.ReportPlayer.builder.email.EmailDtoBuilder;
import com.example.ReportPlayer.builder.password.ForgotPasswordDtoBuilder;
import com.example.ReportPlayer.builder.token.VerificationTokenBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.dto.email.EmailDto;
import com.example.ReportPlayer.dto.password.ForgotPasswordDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.UserNotFoundException;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.email.EmailSenderService;
import com.example.ReportPlayer.services.token.VerificationTokenServiceImpl;
import com.example.ReportPlayer.services.user.UserServiceImpl;
import com.example.ReportPlayer.utils.VerifyToken;
import com.example.ReportPlayer.utils.pojo.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VerificationTokenServiceImpl verificationTokenService;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private EmailSenderService emailSenderService;

    @MockBean
    private VerifyToken verifyToken;

    @Test
    public void email_ShouldSendTokenViaEmail() throws Exception {
        //arrange
        final EmailDto emailDto = EmailDtoBuilder.newBuilder().email("Apollo2@gmail.com").build();
        final User user = UserBuilder.newBuilder().username("Apollo").
                password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken verificationToken = VerificationTokenBuilder.newBuilder().region(Server.Region.EUW).user(user).build();
        //act
        when(userService.findByEmail(anyString())).thenReturn(user);
        when(verificationTokenService.save(any(User.class))).thenReturn(verificationToken);
        doNothing().when(emailSenderService).send(any(Email.class));
        mockMvc.perform(put("/api/v1/accounts/email")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emailDto)))
                .andExpect(status().isOk())
                .andDo(print());
        //assert
        verify(userService,times(1)).findByEmail(anyString());
        verify(verificationTokenService,times(1)).save(any(User.class));
        verify(emailSenderService,times(1)).send(any(Email.class));

    }

    @Test
    public void email_ShouldThrowUserNotFoundException() throws Exception {
        //arrange
        final EmailDto emailDto = EmailDtoBuilder.newBuilder().email("Apollo2@gmail.com").build();
        when(userService.findByEmail(anyString())).thenThrow(UserNotFoundException.class);
        //act
        mockMvc.perform(put("/api/v1/accounts/email")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emailDto)))
                .andExpect(status().isNotFound())
                .andDo(print());
        //assert
        verify(userService,times(1)).findByEmail(anyString());
        verify(verificationTokenService,times(0)).save(any(User.class));
        verify(emailSenderService,times(0)).send(any(Email.class));
    }

    @Test
    public void update_ShouldSavePassword() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo")
                .password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken verificationToken = VerificationTokenBuilder.newBuilder().region(Server.Region.EUW).user(user).build();
        final ForgotPasswordDto forgotPasswordDto = ForgotPasswordDtoBuilder.newBuilder().password("Apollo1010")
                .confirmPassword("Apollo1010").token(verificationToken.getConfirmationToken()).build();
        //act
        when(verificationTokenService.findTokenByConfirmationToken(anyString())).thenReturn(verificationToken);
        when(verifyToken.verify(any(VerificationToken.class))).thenReturn("valid");
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(userService.update(any(User.class))).thenReturn(user);
        mockMvc.perform(delete("/api/v1/accounts/password").
                with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(forgotPasswordDto))).
                andExpect(status().isNoContent()).
                andDo(print());
        //assert
        verify(verificationTokenService,times(1)).findTokenByConfirmationToken(anyString());
        verify(verifyToken,times(1)).verify(any(VerificationToken.class));
        verify(userService,times(1)).findByUsername(anyString());
        verify(userService,times(1)).update(any(User.class));
    }

    @Test
    public void update_ShouldReturnTokenInvalid() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo")
                .password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").isActive(false).build();
        final VerificationToken verificationToken = VerificationTokenBuilder.newBuilder().region(Server.Region.EUW).user(user).build();
        final ForgotPasswordDto forgotPasswordDto = ForgotPasswordDtoBuilder.newBuilder().password("Apollo1010")
                .confirmPassword("Apollo1010").token(verificationToken.getConfirmationToken()).build();
        //act
        when(verificationTokenService.findTokenByConfirmationToken(anyString())).thenReturn(verificationToken);
        when(verifyToken.verify(any(VerificationToken.class))).thenReturn("invalid");
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(userService.update(any(User.class))).thenReturn(user);
        mockMvc.perform(delete("/api/v1/accounts/password").
                with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(forgotPasswordDto))).
                andExpect(status().isUnauthorized()).
                andDo(print());
        //assert
        verify(verificationTokenService,times(1)).findTokenByConfirmationToken(anyString());
        verify(verifyToken,times(1)).verify(any(VerificationToken.class));
        verify(userService,times(0)).findByUsername(anyString());
        verify(userService,times(0)).update(any(User.class));
    }

    @Test
    public void update_ShouldReturnErrorIfPasswordIsBlank() throws Exception {
        //arrange
        final ForgotPasswordDto forgotPasswordDto = ForgotPasswordDtoBuilder.newBuilder().password("").confirmPassword("").build();
        //act
        mockMvc.perform(delete("/api/v1/accounts/password").
                with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(forgotPasswordDto))).
                andExpect(status().isBadRequest()).
                andDo(print());
        //assert
        verify(verificationTokenService,times(0)).findTokenByConfirmationToken(anyString());
        verify(verifyToken,times(0)).verify(any(VerificationToken.class));
        verify(userService,times(0)).findByUsername(anyString());
        verify(userService,times(0)).update(any(User.class));
    }

}
