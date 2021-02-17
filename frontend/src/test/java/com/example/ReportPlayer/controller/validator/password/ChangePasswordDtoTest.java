package com.example.ReportPlayer.controller.validator.password;

import com.example.ReportPlayer.builder.password.ChangePasswordDtoBuilder;
import com.example.ReportPlayer.dto.password.ChangePasswordDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ChangePasswordDtoTest {

    @Autowired
    private Validator validator;


    @Test
    public void password_ShouldValidationPassword() throws Exception {
        //arrange
        final ChangePasswordDto changePasswordDto = ChangePasswordDtoBuilder.newBuilder().
                username("Puudsa911").oldPassword("Tricolore99").password("Tricolore10").confirmPassword("Tricolore10").build();
        //act
        Set<ConstraintViolation<ChangePasswordDto>> violations = validator.validate(changePasswordDto);
        //assert
        System.out.println(violations.toString());
        assertEquals(0,violations.size());
    }

    @Test
    public void password_ShouldInvalidationPassword() throws Exception {
        //arrange
        final ChangePasswordDto changePasswordDto= ChangePasswordDtoBuilder.newBuilder().
                oldPassword("").password("tricolore99").confirmPassword("Tricolore99").build();

        //act
        Set<ConstraintViolation<ChangePasswordDto>> violations = validator.validate(changePasswordDto);
        //assert
        System.out.println(violations.iterator().next().getMessage());
        assertEquals(1,violations.size());
    }

    @Test
    public void password_ShouldInvalidationPasswordWhenTheyNotMatch() throws Exception {
        //arrange
        final ChangePasswordDto changePasswordDto= ChangePasswordDtoBuilder.newBuilder().
                oldPassword("").password("dd").confirmPassword("Tricolore99").build();

        //act
        Set<ConstraintViolation<ChangePasswordDto>> violations = validator.validate(changePasswordDto);
        //assert
        System.out.println(violations.iterator().next().getMessage());
        assertEquals(1,violations.size());
    }

}
