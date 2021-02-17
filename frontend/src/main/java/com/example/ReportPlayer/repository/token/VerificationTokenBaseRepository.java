package com.example.ReportPlayer.repository.token;

import com.example.ReportPlayer.models.token.VerificationToken;
import com.example.ReportPlayer.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("verification_token_repository")
public interface VerificationTokenBaseRepository extends JpaRepository<VerificationToken,Long>{

    VerificationToken findTokenByConfirmationToken(String s);
    VerificationToken findTokenByUser(User user);
    void deleteByConfirmationToken(VerificationToken s);

}
