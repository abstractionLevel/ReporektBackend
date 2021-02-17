package com.example.ReportPlayer.builder.email;

import com.example.ReportPlayer.dto.email.EmailDto;

public class EmailDtoBuilder {

    private String email;

    private EmailDtoBuilder() {
    }

    public static EmailDtoBuilder newBuilder() {
        return new EmailDtoBuilder();
    }

    public  EmailDtoBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmailDto build() {
        return new EmailDto(email);
    }
}
