package com.example.ReportPlayer.builder;

import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.dto.report.ReportDto;

public final class ReportDtoBuilder {

    private String region;
    private String nickname;
    private String descriptionReport;
    private String reportType;
    private long id;
    private User  user;



    private ReportDtoBuilder() {
    }

    public static ReportDtoBuilder newBuilder() {
        return new ReportDtoBuilder();
    }

    public ReportDtoBuilder region(String region) {
        this.region = region;
        return this;
    }

    public ReportDtoBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ReportDtoBuilder id(long id) {
        this.id = id;
        return this;
    }
    public ReportDtoBuilder reportType(String reportType) {
        this.reportType = reportType;
        return this;
    }

    public ReportDtoBuilder description(String descriptionReport) {
        this.descriptionReport = descriptionReport;
        return this;
    }

    public ReportDtoBuilder user(User user) {
        this.user = user;
        return this;
    }

    public ReportDto build() {
        return new ReportDto(id,region,nickname,descriptionReport,reportType,user);
    }

}
