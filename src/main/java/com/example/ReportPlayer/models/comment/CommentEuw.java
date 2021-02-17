package com.example.ReportPlayer.models.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportEuw;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.EUW)
public class CommentEuw extends Comment {

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_euw_id", nullable = false)
    private DescriptionReportEuw descriptionReport;


    public CommentEuw() {}


    @Override
    public DescriptionReportEuw getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportEuw ) descriptionReport;
    }

}
