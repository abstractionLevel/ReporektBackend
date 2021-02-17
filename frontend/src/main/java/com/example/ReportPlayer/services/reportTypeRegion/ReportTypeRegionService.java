package com.example.ReportPlayer.services.reportTypeRegion;

import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;

import java.util.List;

public interface ReportTypeRegionService {

    List<ReportTypeRegion> getAllReportType();
    List<ReportTypeRegion> getAllReportTypeByRegion(String region);
    ReportTypeRegion save(ReportTypeRegion reportTypeRegion);
}
