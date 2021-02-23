package com.example.ReportPlayer.controller.controller.api.user;

import com.example.ReportPlayer.builder.email.EmailDtoBuilder;
import com.example.ReportPlayer.builder.password.ForgotPasswordDtoBuilder;
import com.example.ReportPlayer.builder.token.VerificationTokenBuilder;
import com.example.ReportPlayer.dto.email.EmailDto;
import com.example.ReportPlayer.dto.password.ForgotPasswordDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.InvalidOldPasswordException;
import com.example.ReportPlayer.exception.UserNotFoundException;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.email.EmailSenderService;
import com.example.ReportPlayer.services.token.VerificationTokenServiceImpl;
import com.example.ReportPlayer.builder.password.ChangePasswordDtoBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.dto.password.ChangePasswordDto;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.ReportPlayer.controller.controller.api.JwtToken.TOKEN;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

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

    private String HEADER = "X-Auth";
    private String JWT = TOKEN;


    @Test
    public void password_ShouldChangePassword() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo").
                password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").isActive(false).build();
        final ChangePasswordDto changePasswordDto = ChangePasswordDtoBuilder.newBuilder().
                username("Apollo").oldPassword("Apollo1010").password("Apollo101010").confirmPassword("Apollo101010").build();
        //act
        when(userService.updatePassword(any(ChangePasswordDto.class))).thenReturn(true);
        mvc.perform(delete("/api/v1/users/0/password").
                with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                header(HEADER,JWT).
                content(objectMapper.writeValueAsString(changePasswordDto))).
                andExpect(status().isNoContent()).
                andDo(print());
        //assert
        verify(userService,times(1)).updatePassword(any(ChangePasswordDto.class));
    }

    @Test
    public void password_ShouldThrowOldInvalidException() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo").
                password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").isActive(false).build();
        final ChangePasswordDto changePasswordDto = ChangePasswordDtoBuilder.newBuilder().
                username("Apollo").oldPassword("Apollo1010").password("Apollo101010").confirmPassword("Apollo101010").build();
        //act
        when(userService.updatePassword(any(ChangePasswordDto.class))).thenThrow(InvalidOldPasswordException.class);
        mvc.perform(delete("/api/v1//users/0/password").
                with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                header(HEADER,JWT).
                content(objectMapper.writeValueAsString(changePasswordDto))).
                andExpect(status().isBadRequest()).
                andDo(print());
        //assert
        verify(userService,times(1)).updatePassword(any(ChangePasswordDto.class));
    }

    @Test
    public void username_ShouldReturnNoContent() throws Exception {
        //arrange
        //act
        when(userService.findByUsername(anyString())).thenReturn(null);
        mvc.perform(get("/api/v1/users/username/diomerda"))
                .andDo(print())
                .andExpect(status().isNoContent());
        //assert
        verify(userService,times(1)).findByUsername(anyString());
    }





}
