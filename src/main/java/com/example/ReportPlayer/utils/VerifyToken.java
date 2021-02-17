package com.example.ReportPlayer.utils;

import com.example.ReportPlayer.models.token.VerificationToken;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class VerifyToken {

    private static final String TOKEN_EXPIRED = "expired";
    private static final String TOKEN_INVALID = "invalidToken";
    private static final String TOKEN_VALID = "valid";

    public String verify(VerificationToken token) {
        if (token == null) {
            return TOKEN_INVALID;
        }
        if(dateHasExpired(token.getExpiryDate())) {
            return TOKEN_EXPIRED;
        }
        return TOKEN_VALID;

    }
    private boolean dateHasExpired(Date date) {
        final Calendar cal = Calendar.getInstance();
        if(date.getTime() - cal.getTime().getTime() <= 0) {
            return true;
        }
        return false;
    }
}
