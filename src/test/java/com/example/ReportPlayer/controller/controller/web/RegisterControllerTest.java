package com.example.ReportPlayer.controller.controller.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void formForgotPassword_ShouldSendToken() throws Exception {
        //arrange
        final String token = "123";
        //act
        mockMvc.perform(get("/web/registration/password-reset?token="+token))
                .andExpect(status().isOk())
                .andDo(print());
        //arrange
    }

}
