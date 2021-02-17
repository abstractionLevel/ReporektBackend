package com.example.ReportPlayer.controller.validator;

import com.example.ReportPlayer.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserDtoTest {

    @Autowired
    private Validator validator;


    @Test
    public void user_ShouldValidateUser() throws Exception {
        //arrange
        UserDto userDto = new UserDto( "KErddj99","Tricolor33","Tricolor33","tot@gmail.com");
        //act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        //assert
        System.out.println(violations.toString());
        assertEquals(0,violations.size());
    }
    @Test
    public void user_ShouldInvalidateUser() throws Exception {
        //arrange
        UserDto userDto = new UserDto( "KErddj99","Tricolor3","Tricolor33","tot@gmail.com");
        //act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        //assert
        System.out.println(violations.toString());
        assertEquals(1,violations.size());
    }
}
