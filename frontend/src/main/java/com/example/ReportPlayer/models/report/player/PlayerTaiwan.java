package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportTaiwan;
import com.example.ReportPlayer.models.report.type.ReportTypeTaiwan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_taiwan")
public class PlayerTaiwan extends Player {

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeTaiwan> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportTaiwan> descriptionReport = new ArrayList<>();

    public PlayerTaiwan(String nickname) {
        super(nickname);
    }

    public PlayerTaiwan() {

    }

    public Collection<ReportTypeTaiwan> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeTaiwan> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportTaiwan> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportTaiwan> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
