package com.example.ReportPlayer.builder.report.description;

import com.example.ReportPlayer.factory.report.DescriptionReportFactory;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;

public class DescriptionReportBuilder {

    private String description;
    private String region;
    private String reportType;
    private Player player;
    private User user;
    private long id;

    private DescriptionReportBuilder() {}

    public static DescriptionReportBuilder newBuilder() {
        return new DescriptionReportBuilder();
    }

    public DescriptionReportBuilder region(String region) {
        this.region = region;
        return this;
    }

    public DescriptionReportBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public DescriptionReportBuilder reportType(String reportType) {
        this.reportType = reportType;
        return this;
    }

    public DescriptionReportBuilder id(long id) {
        this.id = id;
        return this;
    }

    public DescriptionReportBuilder description(String description) {
        this.description = description;
        return this;
    }

    public DescriptionReportBuilder user(User user) {
        this.user = user;
        return this;
    }


    public DescriptionReport build() {
        DescriptionReport descriptionReport = DescriptionReportFactory.getDescriptionReport(region);
        descriptionReport.setDescription(description);
        descriptionReport.setReportType(reportType);
        descriptionReport.setPlayer(player);
        descriptionReport.setUser(user);
        descriptionReport.setId(id);
        return descriptionReport;
    }
}
