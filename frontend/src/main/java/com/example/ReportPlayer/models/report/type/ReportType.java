package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  abstract class ReportType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "report_type")
    private String reportType;
    @Column(name = "count")
    private int count;
    @Transient
    private Player player;

    public ReportType() {}
    public ReportType(Player player) {
        this.player = player;
    }
    public ReportType(Player player,int count) {
        this.player = player;
        this.count = count;
    }

    public ReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportType() {
        return reportType;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
