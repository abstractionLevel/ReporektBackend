package com.example.ReportPlayer.controller.validator.password;

import com.example.ReportPlayer.builder.password.ForgotPasswordDtoBuilder;
import com.example.ReportPlayer.dto.password.ForgotPasswordDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ForgotPasswordDtoTest {

    @Autowired
    private Validator validator;

    @Test
    public void password_ShouldValidPassword() throws Exception {
        //arrange
        final ForgotPasswordDto forgotPasswordDto = ForgotPasswordDtoBuilder.newBuilder().password("Apollo1111").confirmPassword("Apollo1111").build();
        //act
        Set<ConstraintViolation<ForgotPasswordDto>> violations = validator.validate(forgotPasswordDto);
        //assert
        assertEquals(0,violations.size());
    }

    @Test
    public void password_ShouldInvalidPassword() throws Exception {
        //arrange
        final ForgotPasswordDto forgotPasswordDto = ForgotPasswordDtoBuilder.newBuilder().password("Apollo1111").confirmPassword("Apollo111").build();

        //act
        Set<ConstraintViolation<ForgotPasswordDto>> violations = validator.validate(forgotPasswordDto);
        //assert
        System.out.println(violations.toString());
        assertEquals(2,violations.size());
    }

}
