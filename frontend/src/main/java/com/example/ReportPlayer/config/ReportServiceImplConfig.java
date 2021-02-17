package com.example.ReportPlayer.config;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.services.report.ReportService;
import com.example.ReportPlayer.services.report.ReportServiceImpl;
import com.example.ReportPlayer.services.report.description.DescriptionReportService;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.services.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportServiceImplConfig {

    @Bean(name = "report_service_"+ Server.Region.EUW)
    public ReportService getReportServiceImplEuw(@Qualifier("player_service_"+ Server.Region.EUW) PlayerService playerService, @Qualifier("description_report_service_"+Server.Region.EUW)DescriptionReportService descriptionReportService,
                                                 @Qualifier("report_type_service_"+ Server.Region.EUW)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.BRAZIL)
    public ReportService getReportServiceImplBrazil( @Qualifier("player_service_"+ Server.Region.BRAZIL)PlayerService playerService,
                                                     @Qualifier("description_report_service_"+Server.Region.BRAZIL)DescriptionReportService descriptionReportService,
                                                     @Qualifier("report_type_service_"+ Server.Region.BRAZIL) ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.EU_NORDIC_AND_EST)
    public ReportService getReportServiceImplEuNe(@Qualifier("player_service_"+ Server.Region.EU_NORDIC_AND_EST)PlayerService playerService,
                                                  @Qualifier("description_report_service_"+Server.Region.EU_NORDIC_AND_EST)DescriptionReportService descriptionReportService,
                                                  @Qualifier("report_type_service_"+ Server.Region.EU_NORDIC_AND_EST)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.JAPAN)
    public ReportService getReportServiceImplJAPAN(@Qualifier("player_service_"+ Server.Region.JAPAN)PlayerService playerService,
                                                   @Qualifier("description_report_service_"+Server.Region.JAPAN)DescriptionReportService descriptionReportService,
                                                   @Qualifier("report_type_service_"+ Server.Region.JAPAN)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.INDONESIA)
    public ReportService getReportServiceImplIndonesia(@Qualifier("player_service_"+ Server.Region.INDONESIA)PlayerService playerService,
                                                       @Qualifier("description_report_service_"+Server.Region.INDONESIA)DescriptionReportService descriptionReportService,
                                                       @Qualifier("report_type_service_"+ Server.Region.INDONESIA)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.KOREA)
    public ReportService getReportServiceImplKorea(@Qualifier("player_service_"+ Server.Region.KOREA)PlayerService playerService,
                                                   @Qualifier("description_report_service_"+Server.Region.KOREA)DescriptionReportService descriptionReportService,
                                                   @Qualifier("report_type_service_"+ Server.Region.KOREA)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.LAN)
    public ReportService getReportServiceImplLan(@Qualifier("player_service_"+ Server.Region.LAN)PlayerService playerService,
                                                 @Qualifier("description_report_service_"+Server.Region.LAN)DescriptionReportService descriptionReportService,
                                                 @Qualifier("report_type_service_"+ Server.Region.LAN)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.LAS)
    public ReportService getReportServiceImplLas(@Qualifier("player_service_"+ Server.Region.LAS)PlayerService playerService,
                                                 @Qualifier("description_report_service_"+Server.Region.LAS)DescriptionReportService descriptionReportService,
                                                 @Qualifier("report_type_service_"+ Server.Region.LAS)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.NORTH_AMERICA)
    public ReportService getReportServiceImplNa(@Qualifier("player_service_"+ Server.Region.NORTH_AMERICA) PlayerService playerService,
                                                @Qualifier("description_report_service_"+Server.Region.NORTH_AMERICA) DescriptionReportService descriptionReportService,
                                                @Qualifier("report_type_service_"+ Server.Region.NORTH_AMERICA)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.OCEANIA)
    public ReportService getReportServiceImplOceania(@Qualifier("player_service_"+ Server.Region.OCEANIA)PlayerService playerService,
                                                     @Qualifier("description_report_service_"+Server.Region.OCEANIA)DescriptionReportService descriptionReportService,
                                                     @Qualifier("report_type_service_"+ Server.Region.OCEANIA)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.PHILIPPINES)
    public ReportService getReportServiceImplPhilippines(@Qualifier("player_service_"+ Server.Region.PHILIPPINES)PlayerService playerService,
                                                         @Qualifier("description_report_service_"+Server.Region.PHILIPPINES)DescriptionReportService descriptionReportService,
                                                         @Qualifier("report_type_service_"+ Server.Region.PHILIPPINES)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.RUSSIA)
    public ReportService getReportServiceImplRussia(@Qualifier("player_service_"+ Server.Region.RUSSIA)PlayerService playerService,
                                                    @Qualifier("description_report_service_"+Server.Region.RUSSIA)DescriptionReportService descriptionReportService,
                                                    @Qualifier("report_type_service_"+ Server.Region.RUSSIA)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.SINGAPORE)
    public ReportService getReportServiceImplSingapore(@Qualifier("player_service_"+ Server.Region.SINGAPORE)PlayerService playerService,
                                                       @Qualifier("description_report_service_"+Server.Region.SINGAPORE)DescriptionReportService descriptionReportService,
                                                       @Qualifier("report_type_service_"+ Server.Region.SINGAPORE)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.TAIWAN)
    public ReportService getReportServiceImplSTaiwan(@Qualifier("player_service_"+ Server.Region.TAIWAN)PlayerService playerService,
                                                     @Qualifier("description_report_service_"+Server.Region.TAIWAN)DescriptionReportService descriptionReportService,
                                                     @Qualifier("report_type_service_"+ Server.Region.TAIWAN)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.THAILAND)
    public ReportService getReportServiceImplThailand(@Qualifier("player_service_"+ Server.Region.THAILAND)PlayerService playerService,
                                                      @Qualifier("description_report_service_"+Server.Region.THAILAND)DescriptionReportService descriptionReportService,
                                                      @Qualifier("report_type_service_"+ Server.Region.THAILAND)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.TURKEY)
    public ReportService getReportServiceImplTurkey(@Qualifier("player_service_"+ Server.Region.TURKEY)PlayerService playerService,
                                                    @Qualifier("description_report_service_"+Server.Region.THAILAND)DescriptionReportService descriptionReportService,
                                                    @Qualifier("report_type_service_"+ Server.Region.THAILAND)ReportTypeService reportTypeService) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }

    @Bean(name = "report_service_"+ Server.Region.VIETNAM)
    public ReportService getReportServiceImplVietnam(@Qualifier("player_service_"+ Server.Region.VIETNAM)PlayerService playerService,
                                                     @Qualifier("description_report_service_"+Server.Region.THAILAND)DescriptionReportService descriptionReportService,
                                                     @Qualifier("report_type_service_"+ Server.Region.THAILAND)ReportTypeService reportTypeService
    ) {
        return new ReportServiceImpl(playerService,descriptionReportService,reportTypeService);
    }
}
