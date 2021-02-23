package com.example.ReportPlayer.config;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.services.report.type.ReportTypeServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportTypeServiceImplConfig {

    @Bean(name = "report_type_service_"+ Server.Region.EUW)
    public ReportTypeService getReportTypeServiceImplEuw(@Qualifier("report_type_repository_"+ Server.Region.EUW)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.BRAZIL)
    public ReportTypeService getReportTypeServiceImplBrazil( @Qualifier("report_type_repository_"+ Server.Region.BRAZIL)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.EU_NORDIC_AND_EST)
    public ReportTypeService getReportTypeServiceImplEuNe(@Qualifier("report_type_repository_"+ Server.Region.EU_NORDIC_AND_EST)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.JAPAN)
    public ReportTypeService getReportTypeServiceImplJAPAN(@Qualifier("report_type_repository_"+ Server.Region.JAPAN)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.INDONESIA)
    public ReportTypeService getReportTypeServiceImplIndonesia(@Qualifier("report_type_repository_"+ Server.Region.INDONESIA)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.KOREA)
    public ReportTypeService getReportTypeServiceImplKorea(@Qualifier("report_type_repository_"+ Server.Region.KOREA)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.LAN)
    public ReportTypeService getReportTypeServiceImplLan(@Qualifier("report_type_repository_"+ Server.Region.LAN)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.LAS)
    public ReportTypeService getReportTypeServiceImplLas(@Qualifier("report_type_repository_"+ Server.Region.LAS)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.NORTH_AMERICA)
    public ReportTypeService getReportTypeServiceImplNa(@Qualifier("report_type_repository_"+ Server.Region.NORTH_AMERICA)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.OCEANIA)
    public ReportTypeService getReportTypeServiceImplOceania(@Qualifier("report_type_repository_"+ Server.Region.OCEANIA)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.PHILIPPINES)
    public ReportTypeService getReportTypeServiceImplPhilippines(@Qualifier("report_type_repository_"+ Server.Region.PHILIPPINES) ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.RUSSIA)
    public ReportTypeService getReportTypeServiceImplRussia(@Qualifier("report_type_repository_"+ Server.Region.RUSSIA)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.SINGAPORE)
    public ReportTypeService getReportTypeServiceImplSingapore(@Qualifier("report_type_repository_"+ Server.Region.SINGAPORE)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.TAIWAN)
    public ReportTypeService getReportTypeServiceImplSTaiwan(@Qualifier("report_type_repository_"+ Server.Region.TAIWAN)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.THAILAND)
    public ReportTypeService getReportTypeServiceImplThailand(@Qualifier("report_type_repository_"+ Server.Region.THAILAND)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.TURKEY)
    public ReportTypeService getReportTypeServiceImplTurkey(@Qualifier("report_type_repository_"+ Server.Region.TURKEY)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }

    @Bean(name = "report_type_service_"+ Server.Region.VIETNAM)
    public ReportTypeService getReportTypeServiceImplVietnam(@Qualifier("report_type_repository_"+ Server.Region.VIETNAM)ReportTypeBaseRepository  repository) {
        return new ReportTypeServiceImpl(repository);
    }
}
