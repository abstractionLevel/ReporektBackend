package com.example.ReportPlayer.dto.password;

import javax.validation.constraints.NotBlank;

public class ChangePasswordDto extends PasswordDto {

    private String username;
    @NotBlank
    private String oldPassword;


    public ChangePasswordDto() {
        super();
    }

    public ChangePasswordDto(String username,String oldPassword, String password, String confirmPassword) {
        super(password,confirmPassword);
        this.oldPassword = oldPassword;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
