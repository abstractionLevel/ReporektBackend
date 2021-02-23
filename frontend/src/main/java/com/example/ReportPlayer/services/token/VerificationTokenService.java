package com.example.ReportPlayer.services.token;

import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.token.VerificationToken;

public interface VerificationTokenService {

    VerificationToken save(User user);
    VerificationToken findTokenByUser(User user);
    VerificationToken findTokenByConfirmationToken(String token);
    void deleteTokenByToken(VerificationToken verificationToken);
}
