package com.example.ReportPlayer.dto.report;



import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.validator.ValidReportType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReportDto {

    private long id;
    private String region;
    @NotBlank
    private String nickname;
    @Size(min = 10,max = 2000)
    private String description;
    @NotBlank
    @ValidReportType
    private String reportType;
    private User user;

    public ReportDto() {}


    public ReportDto(long id,String region, String nickname, String description, String reportType,User user) {
        this.nickname = nickname;
        this.region = region;
        this.description = description;
        this.reportType= reportType;
        this.id = id;
        this.user = user;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
