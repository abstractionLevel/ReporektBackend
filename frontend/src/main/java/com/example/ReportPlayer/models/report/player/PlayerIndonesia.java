package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportIndonesia;
import com.example.ReportPlayer.models.report.type.ReportTypeIndonesia;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_indonesia")
public class PlayerIndonesia extends Player {

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeIndonesia> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportIndonesia> descriptionReport = new ArrayList<>();

    public PlayerIndonesia(String nickname) {
        super(nickname);
    }

    public PlayerIndonesia() {

    }

    public Collection<ReportTypeIndonesia> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeIndonesia> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportIndonesia> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportIndonesia> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
