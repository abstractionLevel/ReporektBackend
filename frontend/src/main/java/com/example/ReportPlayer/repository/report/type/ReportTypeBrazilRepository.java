package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeBrazil;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.BRAZIL)
public interface ReportTypeBrazilRepository extends ReportTypeBaseRepository<ReportTypeBrazil> {
}
