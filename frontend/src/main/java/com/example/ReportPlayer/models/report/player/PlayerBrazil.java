package com.example.ReportPlayer.models.report.player;

import com.example.ReportPlayer.models.comment.CommentBrazil;
import com.example.ReportPlayer.models.report.description.DescriptionReportBrazil;
import com.example.ReportPlayer.models.report.type.ReportTypeBrazil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "player_brazil")
public class PlayerBrazil  extends Player{

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ReportTypeBrazil> reportType = new ArrayList<>();

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DescriptionReportBrazil> descriptionReport = new ArrayList<>();

    public PlayerBrazil(String nickname) {
        super(nickname);
    }

    public PlayerBrazil() { }


    public Collection<ReportTypeBrazil> getReportType() {
        return reportType;
    }

    public void setReportType(Set<ReportTypeBrazil> reportType) {
        this.reportType = reportType;
    }


    public Collection<DescriptionReportBrazil> getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(Collection<DescriptionReportBrazil> descriptionReport) {
        this.descriptionReport= descriptionReport;
    }
}
