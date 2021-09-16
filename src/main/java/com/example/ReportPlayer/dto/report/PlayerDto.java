package com.example.ReportPlayer.dto.report;


import com.example.ReportPlayer.enumerated.Server;

import javax.validation.constraints.NotBlank;

public class PlayerDto {

    @NotBlank
    private String nickname;
    private int reportCount;

    public PlayerDto(String nickname, int reportCount) {
        this.reportCount = reportCount;
        this.nickname = nickname;
    }

    public PlayerDto(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getReportCount() {
        return this.reportCount;
    }
    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }
}
