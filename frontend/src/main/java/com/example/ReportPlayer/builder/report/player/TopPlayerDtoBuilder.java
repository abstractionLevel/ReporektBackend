package com.example.ReportPlayer.builder.report.player;

import com.example.ReportPlayer.dto.report.TopPlayerDto;
import com.example.ReportPlayer.models.report.type.ReportType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopPlayerDtoBuilder {

    private String nickname;
    private int reportCount;
    private List<String> reportType;
    private Date date;
    private long playerId;
    private TopPlayerDtoBuilder() {}

    public static TopPlayerDtoBuilder newBuilder() {
        return new TopPlayerDtoBuilder();
    }

    public TopPlayerDtoBuilder playerId(long playerId) {
        this.playerId = playerId;
        return this;
    }
    public TopPlayerDtoBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public TopPlayerDtoBuilder date(Date date) {
        this.date = date;
        return this;
    }
    public TopPlayerDtoBuilder reportCount(int reportCount) {
        this.reportCount = reportCount;
        return this;
    }

    public TopPlayerDtoBuilder reportType(List<String> reportType) {
        this.reportType = reportType;
        return this;
    }

    public TopPlayerDto build() {
        TopPlayerDto topPlayerDto = new TopPlayerDto();
        topPlayerDto.setReportCount(reportCount);
        topPlayerDto.setNickname(nickname);
        topPlayerDto.setReportType(reportType);
        topPlayerDto.setDate(date);
        topPlayerDto.setPlayerId(playerId);
        return topPlayerDto;
    }
}