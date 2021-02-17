package com.example.ReportPlayer.builder.report.type;

import com.example.ReportPlayer.factory.report.ReportTypeFactory;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;

public class ReportTypeBuilder {

    private String reportType;
    private String region;
    private int count;
    private Player player;

    private ReportTypeBuilder() {}

    public static ReportTypeBuilder newBuilder() {
        return new ReportTypeBuilder();
    }

    public ReportTypeBuilder reportType(String reportType ) {
        this.reportType=reportType;
        return this;
    }

    public ReportTypeBuilder player(Player player) {
        this.player = player;
        return this;
    }
    public ReportTypeBuilder count(int count) {
        this.count = count;
        return this;
    }
    public ReportTypeBuilder region(String region) {
        this.region = region;
        return this;
    }

    public ReportType build() {
        ReportType reportTypeObj = ReportTypeFactory.getReportType(region);
        reportTypeObj.setReportType(reportType);
        reportTypeObj.setCount(count);
        reportTypeObj.setPlayer(player);
        return reportTypeObj;
    }
}
