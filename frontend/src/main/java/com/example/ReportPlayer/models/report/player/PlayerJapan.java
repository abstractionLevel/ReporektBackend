package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportJapan;
import com.example.ReportPlayer.models.report.type.ReportTypeJapan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_japan")
public class PlayerJapan  extends Player{

    public PlayerJapan(String username) {
        super(username);
    }

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeJapan> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportJapan> descriptionReport = new ArrayList<>();

    public PlayerJapan() {

    }

    public Collection<ReportTypeJapan> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeJapan> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportJapan> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReportJapan(Collection<DescriptionReportJapan> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
