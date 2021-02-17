package com.example.ReportPlayer.dto.email;

import com.example.ReportPlayer.validator.ValidEmail;
import com.sun.istack.NotNull;

public class EmailDto {

    @ValidEmail
    @NotNull
    private String email;

    public EmailDto() {}
    public EmailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
