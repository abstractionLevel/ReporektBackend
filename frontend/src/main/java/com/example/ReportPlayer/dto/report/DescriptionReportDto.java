package com.example.ReportPlayer.dto.report;

import com.example.ReportPlayer.factory.report.DescriptionReportFactory;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;

import java.util.Date;

public class DescriptionReportDto {

    private String description;
    private String reportType;
    private Date date;
    private long id;
    private String player;
    private String  user;

    public DescriptionReportDto(String description,String reportType,Date date, long id,String player,String user) {
        this.description = description;
        this.reportType = reportType;
        this.date = date;
        this.id = id;
        this.player = player;
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
