package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.report.description.DescriptionReportVietnam;
import com.example.ReportPlayer.models.report.type.ReportTypeVietnam;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_vietnam")
public class PlayerVietnam extends Player {

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeVietnam> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportVietnam> descriptionReport = new ArrayList<>();

    public PlayerVietnam(String nickname) {
        super(nickname);
    }

    public PlayerVietnam() {

    }

    public Collection<ReportTypeVietnam> getReportType() {
        return reportType;
    }

    public void setReportType(Collection<ReportTypeVietnam> reportType) {
        this.reportType = reportType;
    }

    public Collection<DescriptionReportVietnam> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportVietnam> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
