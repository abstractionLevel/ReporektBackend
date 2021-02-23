package com.example.ReportPlayer.builder.password;

import com.example.ReportPlayer.dto.password.ChangePasswordDto;

public class ChangePasswordDtoBuilder {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;

    private ChangePasswordDtoBuilder() {

    }

    public static ChangePasswordDtoBuilder newBuilder() {
        return new ChangePasswordDtoBuilder();
    }

    public ChangePasswordDtoBuilder username(String username) {
        this.username = username;
        return this;
    }


    public ChangePasswordDtoBuilder oldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public ChangePasswordDtoBuilder password(String password) {
        this.password = password;
        return this;
    }

    public ChangePasswordDtoBuilder confirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public ChangePasswordDto build() {
        return new ChangePasswordDto(username,oldPassword,password,confirmPassword);
    }
}
