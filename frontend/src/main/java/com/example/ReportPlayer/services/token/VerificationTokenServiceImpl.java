package com.example.ReportPlayer.services.token;

import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.repository.token.VerificationTokenBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenBaseRepository repository;

    public VerificationToken save(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        VerificationToken verificationTokenSaved = (VerificationToken) repository.save(verificationToken);
        return verificationTokenSaved;
    }

    @Override
    public void deleteTokenByToken(VerificationToken verificationToken) {
        repository.delete(verificationToken);
    }

    @Override
    public VerificationToken findTokenByUser(User user) {
        return (VerificationToken) repository.findTokenByUser(user);
    }


    @Override
    public VerificationToken findTokenByConfirmationToken(String token) {
        return (VerificationToken) repository.findTokenByConfirmationToken(token);
    }

    private boolean dateHasExpired(Date date) {
        final Calendar cal = Calendar.getInstance();
        if(date.getTime() - cal.getTime().getTime() <= 0) {
            return true;
        }
        return false;
    }




}
