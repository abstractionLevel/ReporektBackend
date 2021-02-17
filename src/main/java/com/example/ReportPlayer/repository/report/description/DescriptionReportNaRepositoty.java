package com.example.ReportPlayer.repository.report.description;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReportNa;
import org.springframework.stereotype.Repository;

@Repository("description_report_repository_"+Server.Region.NORTH_AMERICA)
public interface DescriptionReportNaRepositoty extends DescriptionReportBaseRepository<DescriptionReportNa> {
}
