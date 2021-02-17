package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeNa;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.NORTH_AMERICA)
public interface ReportTypeNaRepository extends ReportTypeBaseRepository<ReportTypeNa> {
}
