package com.example.ReportPlayer.repository.report.description;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReportLas;
import org.springframework.stereotype.Repository;

@Repository("description_report_repository_"+Server.Region.LAS)
public interface DescriptionReportLasRepositoty extends DescriptionReportBaseRepository<DescriptionReportLas> {
}
