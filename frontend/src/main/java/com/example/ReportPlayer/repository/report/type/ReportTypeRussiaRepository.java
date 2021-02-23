package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeRussia;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.RUSSIA)
public interface ReportTypeRussiaRepository extends ReportTypeBaseRepository<ReportTypeRussia> {
}
