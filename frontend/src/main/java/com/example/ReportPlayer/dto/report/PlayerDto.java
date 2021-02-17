package com.example.ReportPlayer.dto.report;


import com.example.ReportPlayer.enumerated.Server;

import javax.validation.constraints.NotBlank;

public class PlayerDto {

    @NotBlank
    private String nickname;
    private Server region;

    public PlayerDto(String nickname, Server region) {
        this.region = region;
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


    public Server getRegion() {
        return this.region;
    }
}
