package com.example.ReportPlayer.services.report.description;

import com.example.ReportPlayer.dto.report.DescriptionReportDto;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import org.springframework.data.domain.Page;

import java.util.List;


public interface DescriptionReportService {

    DescriptionReport save(DescriptionReport descriptionReport);
    void deleteById(long id);
    DescriptionReport findById(long id);
    List<DescriptionReport> getDescriptionReportsByPlayerId(long id);
    List<DescriptionReport> getTheLast10DescriptionReport();

}
