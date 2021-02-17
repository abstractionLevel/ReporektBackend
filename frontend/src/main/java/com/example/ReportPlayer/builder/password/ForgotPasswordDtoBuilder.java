package com.example.ReportPlayer.builder.password;

import com.example.ReportPlayer.dto.password.ForgotPasswordDto;

public class ForgotPasswordDtoBuilder {

    private String password;
    private String confirmPassword;
    private String token;

    private ForgotPasswordDtoBuilder() {}

    public static ForgotPasswordDtoBuilder newBuilder() {
        return new ForgotPasswordDtoBuilder();
    }

    public ForgotPasswordDtoBuilder password(String password) {
        this.password = password;
        return this;
    }

    public ForgotPasswordDtoBuilder token(String token) {
        this.token = token;
        return this;
    }

    public ForgotPasswordDtoBuilder confirmPassword(String confirmPassword) {
        this.confirmPassword= confirmPassword;
        return this;
    }

    public ForgotPasswordDto build() {
        return new ForgotPasswordDto(password,confirmPassword,token);
    }
}
