package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeEuNordicEst;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.EU_NORDIC_AND_EST)
public interface ReportTypeEuNeRepository extends ReportTypeBaseRepository<ReportTypeEuNordicEst> {
}
