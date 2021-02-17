package com.example.ReportPlayer.controller.controller.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerInTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    //login
    @Test
    @Transactional
    public void login_ShouldLogin() throws Exception {
        //arrange
        //act
        MvcResult result = mockMvc.perform(post("/login")
                .param("username","skynet88")
                .param("password","Tricolore999"))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andReturn();
        //assert
        assertEquals("/welcome",result.getResponse().getRedirectedUrl());
    }

    @ParameterizedTest(name = "{index} => username={0}, password={1}")
    @MethodSource("getInvalidLogin")
    public void login_ShouldReturnRedirectUrlIfPassOrUsernameAreInvalid(String username, String password) throws Exception{
        //arrange
        //act
        MvcResult result = mockMvc.perform(post("/login")
                .param("username",username)
                .param("password",password))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andReturn();
        //assert
        assertEquals("/login?error",result.getResponse().getRedirectedUrl());
    }

    private static Stream<Arguments> getInvalidLogin() {
        return Stream.of(
                Arguments.of("","password"),
                Arguments.of("usernameee","password"),
                Arguments.of("fffff","password")

        );
    }
}
