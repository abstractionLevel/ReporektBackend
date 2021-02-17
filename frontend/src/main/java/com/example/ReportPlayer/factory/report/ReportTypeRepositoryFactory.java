package com.example.ReportPlayer.factory.report;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import org.springframework.context.ApplicationContext;

public class ReportTypeRepositoryFactory {


    public static ReportTypeBaseRepository getRepository(String region, ApplicationContext context) {
        ReportTypeBaseRepository repository = (ReportTypeBaseRepository) context.getBean("report_type_repository_"+region);
        if(repository == null)
            return null;
        return repository;
    }
}
