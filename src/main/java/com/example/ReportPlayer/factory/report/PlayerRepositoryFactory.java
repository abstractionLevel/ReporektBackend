package com.example.ReportPlayer.factory.report;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;
import org.springframework.context.ApplicationContext;

public class PlayerRepositoryFactory  {

    public static PlayerBaseRepository getRepository(String region,ApplicationContext context) {
        PlayerBaseRepository repository = (PlayerBaseRepository) context.getBean("player_repository_"+region);;
        if(repository ==  null)
            return null;
        return repository;
    }


}
