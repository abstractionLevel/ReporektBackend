package com.example.ReportPlayer.factory.user;

import com.example.ReportPlayer.repository.user.UserBaseRepository;
import org.springframework.context.ApplicationContext;

public class UserRepositoryFactory {


    public static UserBaseRepository getRepository(String region, ApplicationContext applicationContext) {
        final UserBaseRepository userBaseRepository = (UserBaseRepository) applicationContext.getBean("user_repository_"+region);
        if (userBaseRepository==null)
            return null;
        return userBaseRepository;

    }
}
