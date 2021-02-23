package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypePhilippines;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.PHILIPPINES)
public interface ReportTypePhilippinesRepository extends ReportTypeBaseRepository<ReportTypePhilippines> {
}
