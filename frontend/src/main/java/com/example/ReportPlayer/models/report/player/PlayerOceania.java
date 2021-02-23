package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportOceania;
import com.example.ReportPlayer.models.report.type.ReportTypeOceania;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_oceania")
public class PlayerOceania extends Player{

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeOceania> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportOceania> descriptionReport = new ArrayList<>();

    public PlayerOceania(String nickname) {
        super(nickname);
    }

    public PlayerOceania() {

    }

    public Collection<ReportTypeOceania> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeOceania> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportOceania> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportOceania> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
