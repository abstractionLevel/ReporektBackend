package com.example.ReportPlayer.dto.password;

import com.example.ReportPlayer.validator.PasswordMatches;
import com.example.ReportPlayer.validator.ValidPassword;
import javax.validation.constraints.NotBlank;

@PasswordMatches(password = "password",confirmPassword = "confirmPassword")
public class PasswordDto {
    @ValidPassword
    @NotBlank
    private String password;
    private String confirmPassword;

    public PasswordDto(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public PasswordDto() {

    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
