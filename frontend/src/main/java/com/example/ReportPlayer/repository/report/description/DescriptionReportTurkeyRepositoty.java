package com.example.ReportPlayer.repository.report.description;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReportTurkey;
import org.springframework.stereotype.Repository;

@Repository("description_report_repository_"+Server.Region.TURKEY)
public interface DescriptionReportTurkeyRepositoty  extends DescriptionReportBaseRepository<DescriptionReportTurkey>{
}
