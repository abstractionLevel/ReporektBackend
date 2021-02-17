package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportKorea;
import com.example.ReportPlayer.models.report.type.ReportTypeKorea;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_korea")
public class PlayerKorea extends Player{

    public PlayerKorea(){}

    public PlayerKorea(String username) {
        super(username);
    }

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeKorea> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportKorea> descriptionReport = new ArrayList<>();

    public Collection<ReportTypeKorea> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeKorea> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportKorea> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReportKorea(Collection<DescriptionReportKorea> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
