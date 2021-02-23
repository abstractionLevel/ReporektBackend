package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportTurkey;
import com.example.ReportPlayer.models.report.type.ReportTypeTurkey;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_turkey")
public class PlayerTurkey extends Player {
    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeTurkey> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportTurkey> descriptionReport = new ArrayList<>();

    public PlayerTurkey(String nickname) {
        super(nickname);
    }

    public PlayerTurkey() {

    }

    public Collection<ReportTypeTurkey> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeTurkey> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportTurkey> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportTurkey> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
