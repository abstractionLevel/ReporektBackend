package com.example.ReportPlayer.dto.request;

import javax.validation.constraints.NotNull;

public class ContactUsDto {

    @NotNull
    private String type;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private String user;
    @NotNull
    private String captcha;

    public ContactUsDto(String type,String title , String description) {
        this.type  = type;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
