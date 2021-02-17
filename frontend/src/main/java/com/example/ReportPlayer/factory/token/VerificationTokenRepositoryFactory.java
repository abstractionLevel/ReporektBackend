package com.example.ReportPlayer.factory.token;

import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import com.example.ReportPlayer.repository.token.VerificationTokenBaseRepository;
import org.springframework.context.ApplicationContext;

public class VerificationTokenRepositoryFactory {

    public static VerificationTokenBaseRepository getRepository(String region, ApplicationContext context) {
        VerificationTokenBaseRepository repository = (VerificationTokenBaseRepository) context.getBean("verification_token_repository_"+region);
        if(repository == null)
            return null;
        return repository;
    }
}
