package com.example.ReportPlayer.repository.report.description;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReportEuNordicEst;
import org.springframework.stereotype.Repository;

@Repository("description_report_repository_"+Server.Region.EU_NORDIC_AND_EST)
public interface DescriptionReportEuNeRepositoty extends DescriptionReportBaseRepository<DescriptionReportEuNordicEst>{
}
