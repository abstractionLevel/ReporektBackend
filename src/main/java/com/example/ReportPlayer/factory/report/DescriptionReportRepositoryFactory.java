package com.example.ReportPlayer.factory.report;

import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import org.springframework.context.ApplicationContext;

public class DescriptionReportRepositoryFactory {


    public static DescriptionReportBaseRepository getRepository(String region, ApplicationContext context) {
        DescriptionReportBaseRepository repository = (DescriptionReportBaseRepository) context.getBean("description_report_repository_"+region);
        if(repository == null) {
            return null;
        }
        return repository;

    }
}
