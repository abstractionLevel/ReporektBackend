package com.example.ReportPlayer.builder.report.player;

import com.example.ReportPlayer.factory.report.PlayerFactory;
import com.example.ReportPlayer.models.report.player.Player;

import java.util.Date;

public  class PlayerBuilder {

    private String username;
    private String region;
    private long id;
    private int reportCount;
    private Date date;

    private PlayerBuilder() {}

    public static PlayerBuilder newBuilder() {
        return new PlayerBuilder();
    }

    public PlayerBuilder username(String username) {
        this.username = username;
        return this;
    }


    public PlayerBuilder id(long id) {
        this.id = this.id;
        return this;
    }
    public PlayerBuilder region(String region) {
        this.region = region;
        return this;
    }

    public PlayerBuilder reportCount(int reportCount) {
        this.reportCount = reportCount;
        return this;
    }

    public PlayerBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public Player build() {
        Player player = PlayerFactory.getPlayer(region);
        player.setNickname(username);
        player.setId(id);
        player.setReportCount(reportCount);
        player.setDate(date);
        return player;
    }
}
