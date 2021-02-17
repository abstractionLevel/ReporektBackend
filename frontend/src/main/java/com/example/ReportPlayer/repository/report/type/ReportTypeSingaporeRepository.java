package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeSingapore;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.SINGAPORE)
public interface ReportTypeSingaporeRepository extends ReportTypeBaseRepository<ReportTypeSingapore> {
}
