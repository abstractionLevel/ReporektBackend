package com.example.ReportPlayer.controller.utility;

import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.utils.VerifyToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class VerifyTokenTest {

    @Autowired
    private VerifyToken verifyToken;

    @Test
    public void verify_ShouldVerifyTokenIsValid() throws Exception {
        //arrange
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(new User());
        //act
        String expectedToken = verifyToken.verify(verificationToken);
        //assert
        assertEquals("valid",expectedToken);
    }

    @Test
    public void verify_ShouldVerifyTokenIsExpired() throws Exception {
        //arrange
        final VerificationToken verificationToken = new VerificationToken();
        verificationToken.setExpiryDate(expiryDate());
        //act
        String expectedToken = verifyToken.verify(verificationToken);
        //assert
        assertEquals("expired",expectedToken);
    }

    private Date expiryDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, -(60*24));
        final Date date = new Date(calendar.getTime().getTime());
        return date;
    }

}
