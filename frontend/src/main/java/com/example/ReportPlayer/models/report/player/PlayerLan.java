package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportLan;
import com.example.ReportPlayer.models.report.type.ReportTypeLan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_lan")
public class PlayerLan  extends Player{

    public PlayerLan(String username) {
        super(username);
    }

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeLan> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportLan> descriptionReport = new ArrayList<>();

    public PlayerLan() {

    }

    public Collection<ReportTypeLan> getReportType() {
        return reportType;
    }

    public void setReportType(Set<ReportTypeLan> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportLan> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReportKorea(Set<DescriptionReportLan> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
