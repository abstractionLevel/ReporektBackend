package com.example.ReportPlayer.dto.report;

import com.example.ReportPlayer.models.report.type.ReportType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopPlayerDto {

    private String nickname;
    private long playerId;
    private int reportCount;
    private Date date;
    private List<String> reportType = new  ArrayList<>();


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public List<String> getReportType() {
        return reportType;
    }

    public void setReportType(List<String> reportType) {
        this.reportType = reportType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
