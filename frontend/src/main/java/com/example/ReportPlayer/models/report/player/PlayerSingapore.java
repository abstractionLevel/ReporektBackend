package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportSingapore;
import com.example.ReportPlayer.models.report.type.ReportTypeSingapore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_singapore")
public class PlayerSingapore extends Player{

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeSingapore> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportSingapore> descriptionReport = new ArrayList<>();

    public PlayerSingapore(String nickname) {
        super(nickname);
    }

    public PlayerSingapore() {

    }

    public Collection<ReportTypeSingapore> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeSingapore> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportSingapore> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportSingapore> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
