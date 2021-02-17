package com.example.ReportPlayer.builder.user;

import com.example.ReportPlayer.models.user.User;

public class UserBuilder {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean isActive;


    private UserBuilder() {}

    public static UserBuilder newBuilder() {
        return new UserBuilder();
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder email(String email) {
        this.email= email;
        return this;
    }



    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder confirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public UserBuilder isActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public User build() {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setConfirmPassword(confirmPassword);
        return user;
    }
}
