package com.example.ReportPlayer.models.report.player;


import com.example.ReportPlayer.models.report.description.DescriptionReportRussia;
import com.example.ReportPlayer.models.report.type.ReportTypeRussia;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_russia")
public class PlayerRussia extends Player{

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeRussia> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportRussia> descriptionReport = new ArrayList<>();

    public PlayerRussia(String nickname) {
        super(nickname);
    }

    public PlayerRussia() {

    }

    public Collection<ReportTypeRussia> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeRussia> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportRussia> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportRussia> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
