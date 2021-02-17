package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeTurkey;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.TURKEY)
public interface ReportTypeTurkeyRepository extends ReportTypeBaseRepository<ReportTypeTurkey> {
}
