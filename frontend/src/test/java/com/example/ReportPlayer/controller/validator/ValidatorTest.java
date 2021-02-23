package com.example.ReportPlayer.controller.validator;

import com.example.ReportPlayer.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    public void email_ShouldValidate() throws Exception {
        //arrange
        final UserDto userDto = adValidUserDto();
        //act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        //assert
        assertEquals(0,violations.size());
    }
    @ParameterizedTest
    @MethodSource("adInvalidEmail")
    public void email_ShouldReturnErrorWhenPassingWrongEmail(UserDto userDto) {
        //arrange
        //act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        //assert
        System.out.println(violations);
        assertEquals(1,violations.size());
    }

    @Test
    public void password_ShouldPassNoErrorPassword() throws Exception {
        //arrange
        final UserDto userDto = adValidUserDto();
        //act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        //assert
        System.out.println(violations);
        assertEquals(0,violations.size());

    }

    @ParameterizedTest
    @MethodSource("adInvalidPassword")
    public void password_ShouldReturnErrorWhenPassingWrongPassword(UserDto userDto) {
        //arrange
        //act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        //assert
        System.out.println(violations);
        assertEquals(1,violations.size());
    }
    private static Stream<UserDto> adInvalidPassword() {
        return Stream.of(
                new UserDto("foofdsfdsfsdfdsfsdf","12345678","12345678","foo@gmail.com"),
                new UserDto("foofdsfdsfsdfdsfsdf","aa12345678","aa12345678","foo@gmail.com"),
                new UserDto("foofdsfdsfsdfdsfsdf","xnbhjhaaq","xnbhjhaaq","foo@gmail.com"),
                new UserDto("foofdsfdsfsdfdsfsdf","aa12345678","aa12345678","foo@gmail.com"),
                new UserDto("foofdsfdsfsdfdsfsdf","COCCCOAAD","COCCCOAAD","foo@gmail.com")
        );
    }
    private static Stream<UserDto> adInvalidEmail() {
        return Stream.of(
                new UserDto("foofdsfdsfsdfdsfsdf","Pipooooooooo88","Pipooooooooo88","@gmail.com"),
                new UserDto("foofdsfdsfsdfdsfsdf","Pipooooooooo88","Pipooooooooo88","pippo@.com"),
                new UserDto("foofdsfdsfsdfdsfsdf","Pipooooooooo88","Pipooooooooo88","pippo@gmail"),
                new UserDto("foofdsfdsfsdfdsfsdf","Pipooooooooo88","Pipooooooooo88","")
        );
    }
    private static UserDto adValidUserDto() {
        return new UserDto("foofdsfdsfsdfdsfsdf","Pipooooooooo1","Pipooooooooo1","pippo@gmail.com");
    }

}
