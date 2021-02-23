package com.example.ReportPlayer.repository.report.description;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReportIndonesia;
import org.springframework.stereotype.Repository;


@Repository("description_report_repository_"+Server.Region.INDONESIA)
public interface DescriptionReportIndonesiaRepositoty extends DescriptionReportBaseRepository<DescriptionReportIndonesia> {
}
