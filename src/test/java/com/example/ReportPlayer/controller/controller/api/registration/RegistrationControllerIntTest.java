package com.example.ReportPlayer.controller.controller.api.registration;


import com.example.ReportPlayer.builder.user.UserDtoBuilder;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.repository.user.UserBaseRepository;
import com.example.ReportPlayer.services.token.VerificationTokenService;
import com.example.ReportPlayer.services.user.UserService;
import com.example.ReportPlayer.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIntTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService service;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private UserBaseRepository userRepository;


    @Test
    public void registerUser_ShouldRegisterUser() throws Exception {
        //arrange
        final UserDto userDto =  UserDtoBuilder.newBuilder().username("Apollo11").

                password("Apollo1010").confirmPassword("Apollo1010").email("apollo@gmail.com").build();

        //act
        mockMvc.perform(post("/api/public/v1/registration/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //assert


    }

    @Test
    public void shouldEnableUser() throws Exception {
        //arrange
        String token="1234567";
        //act
        MvcResult result = mockMvc.perform(get("/api/user/confirm-account").param("token",token))
                .andExpect(status().isOk())
                .andReturn();
        //assert
        final User expected = service.findByUsername("skynet");
    }





}
