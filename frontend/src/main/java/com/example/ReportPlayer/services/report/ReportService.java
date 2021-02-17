package com.example.ReportPlayer.services.report;

import com.example.ReportPlayer.dto.report.ReportDto;

import java.util.List;

public interface ReportService {

    ReportDto createReport(ReportDto reportDto);

    void delete(long idDescriptionReport);

    ReportDto getReportById(long idDescriptionReport);

    List<ReportDto> getPaginationDescription(long playerId);
}
