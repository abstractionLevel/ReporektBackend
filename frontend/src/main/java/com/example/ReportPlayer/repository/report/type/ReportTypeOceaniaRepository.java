package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.ReportTypeOceania;
import org.springframework.stereotype.Repository;

@Repository("report_type_repository_"+ Server.Region.OCEANIA)
public interface ReportTypeOceaniaRepository extends ReportTypeBaseRepository<ReportTypeOceania> {
}
