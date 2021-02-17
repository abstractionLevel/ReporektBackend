package com.example.ReportPlayer.models.report.player;


import com.example.ReportPlayer.models.report.description.DescriptionReportThailand;
import com.example.ReportPlayer.models.report.type.ReportTypeThailand;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_thailand")
public class PlayerThailand extends Player {
    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeThailand> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportThailand> descriptionReport = new ArrayList<>();

    public PlayerThailand(String nickname) {
        super(nickname);
    }

    public PlayerThailand() {

    }

    public Collection<ReportTypeThailand> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeThailand> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportThailand> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportThailand> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
