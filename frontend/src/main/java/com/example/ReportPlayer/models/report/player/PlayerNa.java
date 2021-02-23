package com.example.ReportPlayer.models.report.player;


import com.example.ReportPlayer.models.report.description.DescriptionReportNa;
import com.example.ReportPlayer.models.report.type.ReportTypeNa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_na")
public class PlayerNa extends Player {

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeNa> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportNa> descriptionReport = new ArrayList<>();

    public PlayerNa(String nickname) {
        super(nickname);
    }

    public PlayerNa() {

    }

    public Collection<ReportTypeNa> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeNa> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportNa> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportNa> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
