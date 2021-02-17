package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeLas;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.LAS)
public interface ReportTypeLasRepository extends ReportTypeBaseRepository<ReportTypeLas> {
}
