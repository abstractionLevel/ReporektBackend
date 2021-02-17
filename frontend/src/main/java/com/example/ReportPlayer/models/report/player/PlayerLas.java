package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportLas;
import com.example.ReportPlayer.models.report.type.ReportTypeLas;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_las")
public class PlayerLas extends Player{

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeLas> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportLas> descriptionReport = new ArrayList<>();

    public PlayerLas(String nickname) {
        super(nickname);
    }

    public PlayerLas() {

    }

    public Collection<ReportTypeLas> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeLas> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportLas> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportLas> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
