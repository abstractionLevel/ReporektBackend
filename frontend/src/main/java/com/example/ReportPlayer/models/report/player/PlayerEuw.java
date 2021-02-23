package com.example.ReportPlayer.models.report.player;


import com.example.ReportPlayer.models.report.description.DescriptionReportEuw;
import com.example.ReportPlayer.models.report.type.ReportTypeEuw;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_euw")
public class PlayerEuw extends Player {



    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeEuw> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportEuw> descriptionReport = new ArrayList<>();

    public PlayerEuw(String nickname) {
        super(nickname);
    }

    public PlayerEuw() {

    }

    public Collection<ReportTypeEuw> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeEuw> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportEuw> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportEuw> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
