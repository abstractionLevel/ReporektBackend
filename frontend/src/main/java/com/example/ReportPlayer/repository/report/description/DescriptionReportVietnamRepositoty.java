package com.example.ReportPlayer.repository.report.description;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReportVietnam;
import org.springframework.stereotype.Repository;

@Repository("description_report_repository_"+Server.Region.VIETNAM)
public interface DescriptionReportVietnamRepositoty extends DescriptionReportBaseRepository<DescriptionReportVietnam> {
}
