package com.example.ReportPlayer.controller.dto;

import com.example.ReportPlayer.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserDtoTest {

    @Test
    public void shouldTestAttribute() throws Exception {
        //arrange
        final UserDto userDto = new UserDto( "sky","nap","nap","ugo@gmail.com");
        //act
        //assert
        assertEquals(userDto.getUsername(),"sky");
        assertEquals(userDto.getPassword(),"nap");
        assertEquals(userDto.getConfirmPassword(),"nap");
        assertEquals(userDto.getEmail(),"ugo@gmail.com");
    }



}
