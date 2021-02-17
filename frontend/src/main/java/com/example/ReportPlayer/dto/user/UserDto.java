package com.example.ReportPlayer.dto.user;

import com.example.ReportPlayer.validator.PasswordMatches;
import com.example.ReportPlayer.validator.ValidEmail;
import com.example.ReportPlayer.validator.ValidPassword;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@PasswordMatches(password = "password",confirmPassword = "confirmPassword")
public class UserDto {
    @NotNull
    @Size(min=8, message = "{Size.userDto.username}")
    private String username;
    @ValidEmail
    @NotNull
    private String email;
    @ValidPassword
    @NotBlank
    private String password;
    private String confirmPassword;

    public UserDto() {}


    public UserDto( String  username, String password, String confirmPassword, String email) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
