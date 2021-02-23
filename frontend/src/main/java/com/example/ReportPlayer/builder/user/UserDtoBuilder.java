package com.example.ReportPlayer.builder.user;

import com.example.ReportPlayer.dto.user.UserDto;

public class UserDtoBuilder {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean isActive;


    private UserDtoBuilder() {}

    public static UserDtoBuilder newBuilder() {
        return new UserDtoBuilder();
    }

    public UserDtoBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder email(String email) {
        this.email= email;
        return this;
    }


    public UserDtoBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserDtoBuilder confirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public UserDtoBuilder isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public UserDto build() {
        return new UserDto(username,password,confirmPassword,email);
    }
}
