package com.example.ReportPlayer.config;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.player.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerServiceImplConfig {

    @Bean(name = "player_service_"+ Server.Region.EUW)
    public PlayerService getPlayerServiceImplEuw(@Qualifier("player_repository_"+Server.Region.EUW)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.BRAZIL)
    public PlayerService getPlayerServiceImplBrazil(@Qualifier("player_repository_"+Server.Region.BRAZIL)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.EU_NORDIC_AND_EST)
    public PlayerService getPlayerServiceImplEuNe(@Qualifier("player_repository_"+Server.Region.EU_NORDIC_AND_EST)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.JAPAN)
    public PlayerService getPlayerServiceImplJAPAN(@Qualifier("player_repository_"+Server.Region.JAPAN)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.INDONESIA)
    public PlayerService getPlayerServiceImplIndonesia(@Qualifier("player_repository_"+Server.Region.INDONESIA)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.KOREA)
    public PlayerService getPlayerServiceImplKorea(@Qualifier("player_repository_"+Server.Region.KOREA)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.LAN)
    public PlayerService getPlayerServiceImplLan(@Qualifier("player_repository_"+Server.Region.LAN)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.LAS)
    public PlayerService getPlayerServiceImplLas(@Qualifier("player_repository_"+Server.Region.LAS)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.NORTH_AMERICA)
    public PlayerService getPlayerServiceImplNa(@Qualifier("player_repository_"+Server.Region.NORTH_AMERICA)PlayerBaseRepository repository ) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.OCEANIA)
    public PlayerService getPlayerServiceImplOceania(@Qualifier("player_repository_"+Server.Region.OCEANIA)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.PHILIPPINES)
    public PlayerService getPlayerServiceImplPhilippines(@Qualifier("player_repository_"+Server.Region.PHILIPPINES)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.RUSSIA)
    public PlayerService getPlayerServiceImplRussia(@Qualifier("player_repository_"+Server.Region.RUSSIA)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.SINGAPORE)
    public PlayerService getPlayerServiceImplSingapore(@Qualifier("player_repository_"+Server.Region.SINGAPORE)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.TAIWAN)
    public PlayerService getPlayerServiceImplSTaiwan(@Qualifier("player_repository_"+Server.Region.TAIWAN)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.THAILAND)
    public PlayerService getPlayerServiceImplThailand(@Qualifier("player_repository_"+Server.Region.THAILAND)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.TURKEY)
    public PlayerService getPlayerServiceImplTurkey(@Qualifier("player_repository_"+Server.Region.TURKEY)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }

    @Bean(name = "player_service_"+ Server.Region.VIETNAM)
    public PlayerService getPlayerServiceImplVietnam(@Qualifier("player_repository_"+Server.Region.VIETNAM)PlayerBaseRepository repository) {
        return new PlayerServiceImpl(repository);
    }
}
