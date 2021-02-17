package com.example.ReportPlayer.builder.token;

import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.token.VerificationToken;

public class VerificationTokenBuilder {

    private User user;
    private String region;


    private VerificationTokenBuilder() {}

    public static VerificationTokenBuilder newBuilder() {
        return new VerificationTokenBuilder();
    }

    public VerificationTokenBuilder region(String region) {
        this.region = region;
        return this;
    }

    public VerificationTokenBuilder user(User user) {
        this.user = user;
        return this;
    }
    public VerificationToken build() {
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        return token;
    }
}
