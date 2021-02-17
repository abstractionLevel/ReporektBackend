package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportEuNordicEst;
import com.example.ReportPlayer.models.report.type.ReportTypeEuNordicEst;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_eu_nordic_est")
public class PlayerEuNordicEst extends Player{
    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeEuNordicEst> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportEuNordicEst> descriptionReport = new ArrayList<>();

    public PlayerEuNordicEst(String nickname) {
        super(nickname);
    }

    public PlayerEuNordicEst() {

    }

    public Collection<ReportTypeEuNordicEst> getReportType() {
        return reportType;
    }

    public void setReportType(Set<ReportTypeEuNordicEst> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportEuNordicEst> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Set<DescriptionReportEuNordicEst> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
