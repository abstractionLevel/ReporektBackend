package com.example.ReportPlayer.config;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import com.example.ReportPlayer.services.report.description.DescriptionReportService;

import com.example.ReportPlayer.services.report.description.DescriptionReportServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DescriptionServiceImplConfig {



    @Bean(name = "description_report_service_"+ Server.Region.EUW)
    public DescriptionReportService getDescriptionServiceImplEuw(@Qualifier("description_report_repository_"+Server.Region.EUW) DescriptionReportBaseRepository repository) {
        return new DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.BRAZIL)
    public DescriptionReportService getDescriptionServiceImplBrazil(@Qualifier("description_report_repository_"+Server.Region.BRAZIL) DescriptionReportBaseRepository repository) {
        return new DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.EU_NORDIC_AND_EST)
    public DescriptionReportService getDescriptionServiceImplEuNe(@Qualifier("description_report_repository_"+Server.Region.EU_NORDIC_AND_EST) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.JAPAN)
    public DescriptionReportService getDescriptionServiceImplJAPAN(@Qualifier("description_report_repository_"+Server.Region.JAPAN) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.INDONESIA)
    public DescriptionReportService getDescriptionServiceImplIndonesia(@Qualifier("description_report_repository_"+Server.Region.INDONESIA) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.KOREA)
    public DescriptionReportService getDescriptionServiceImplKorea(@Qualifier("description_report_repository_"+Server.Region.KOREA) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.LAN)
    public DescriptionReportService getDescriptionServiceImplLan(@Qualifier("description_report_repository_"+Server.Region.LAN) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.LAS)
    public DescriptionReportService getDescriptionServiceImplLas(@Qualifier("description_report_repository_"+Server.Region.LAS) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.NORTH_AMERICA)
    public DescriptionReportService getDescriptionServiceImplNa(@Qualifier("description_report_repository_"+Server.Region.NORTH_AMERICA) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.OCEANIA)
    public DescriptionReportService getDescriptionServiceImplOceania(@Qualifier("description_report_repository_"+Server.Region.OCEANIA) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.PHILIPPINES)
    public DescriptionReportService getDescriptionServiceImplPhilippines(@Qualifier("description_report_repository_"+Server.Region.PHILIPPINES) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.RUSSIA)
    public DescriptionReportService getDescriptionServiceImplRussia(@Qualifier("description_report_repository_"+Server.Region.RUSSIA) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.SINGAPORE)
    public DescriptionReportService getDescriptionServiceImplSingapore(@Qualifier("description_report_repository_"+Server.Region.SINGAPORE) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.TAIWAN)
    public DescriptionReportService getDescriptionServiceImplSTaiwan(@Qualifier("description_report_repository_"+Server.Region.TAIWAN) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.THAILAND)
    public DescriptionReportService getDescriptionServiceImplThailand(@Qualifier("description_report_repository_"+Server.Region.THAILAND) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.TURKEY)
    public DescriptionReportService getDescriptionServiceImplTurkey(@Qualifier("description_report_repository_"+Server.Region.TURKEY) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }

    @Bean(name = "description_report_service_"+ Server.Region.VIETNAM)
    public DescriptionReportService getDescriptionServiceImplVietnam(@Qualifier("description_report_repository_"+Server.Region.VIETNAM) DescriptionReportBaseRepository repository) {
        return new  DescriptionReportServiceImpl(repository);
    }
}
