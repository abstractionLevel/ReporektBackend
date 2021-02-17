package com.example.ReportPlayer.controller.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class EndPointAccessSecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void home_ShouldSucceedWith302() throws Exception {
        //arrange
        //act
        mvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"))
                .andDo(print())
                .andReturn();
        //assert
    }

    @Test
    @WithMockUser(username="RyzeTheBelaba22",roles={"USER"})
    public void home_ShouldSucceedWith200() throws Exception {
        //arrange
        //act
        mvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andDo(print())
                .andReturn();
        //assert
    }

    @Test
    public void login_ShouldSucceedWith200() throws Exception {
        //arrange
        //act
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print())
                .andReturn();
        //assert
    }

    @Test
    public void register_ShouldSucceedWith200() throws Exception {
        //arrange
        //act
        mvc.perform(MockMvcRequestBuilders.get("/api/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andDo(print())
                .andReturn();
        //assert
    }

    @Test
    public void confirmAccount_ShouldSucceedWith200() throws Exception {
        //arrange
        //act
        mvc.perform(MockMvcRequestBuilders.get("/api/user/confirm-account"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
        //assert
    }



}
