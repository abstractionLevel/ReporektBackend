package com.example.ReportPlayer.controller.services.user;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.user.UserServiceImpl;
import com.example.ReportPlayer.builder.user.UserDtoBuilder;
import com.example.ReportPlayer.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserServiceImplIntTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void save_ShouldSave() throws Exception {
        //arrange
        final UserDto userDto = UserDtoBuilder.newBuilder().email("deluciaugo@gmail.com").password("Apollo12").
                confirmPassword("Apollo12").username("Apollo121124").isActive(false).build();
        //act
        final User user = userService.save(userDto);
        //assert
    }

    @Test
    public void find_ShouldFindByUsername() throws Exception {
        //arrange
        //act
        final User user = userService.findByUsername("Apollo12112");
        //assert
        assertEquals("Apollo12112",user.getUsername());
    }

    @Test
    public void find_ShouldFindByEmail() throws Exception {
        //arrange
        //act
        final User user = userService.findByEmail("deluciaugo@gmail.com");
        //assert
        assertEquals("Apollo12112",user.getUsername());
    }

}
