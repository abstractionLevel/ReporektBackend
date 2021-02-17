package com.example.ReportPlayer.dto.report;

import com.example.ReportPlayer.validator.ValidReportType;

import javax.validation.constraints.NotBlank;

public class ReportTypeDto {

    @NotBlank
    @ValidReportType
    private String reportType;
    private int count;

    public ReportTypeDto() {

    }
    public ReportTypeDto(String reportType,int count) {
        this.reportType = reportType;
        this.count = count;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
