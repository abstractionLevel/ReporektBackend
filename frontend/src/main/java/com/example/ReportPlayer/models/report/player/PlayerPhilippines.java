package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportPhilippines;
import com.example.ReportPlayer.models.report.type.ReportTypePhilippines;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_philippines")
public class PlayerPhilippines extends Player {

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypePhilippines> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportPhilippines> descriptionReport = new ArrayList<>();

    public PlayerPhilippines(String nickname) {
        super(nickname);
    }

    public PlayerPhilippines() {

    }

    public Collection<ReportTypePhilippines> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypePhilippines> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportPhilippines> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportPhilippines> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
