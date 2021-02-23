package com.example.ReportPlayer.models.comment;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportTaiwan;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.TAIWAN)
public class CommentTaiwan extends Comment{

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_taiwan_id", nullable = false)
    private DescriptionReportTaiwan descriptionReport;

    public CommentTaiwan() {}

    @Override
    public DescriptionReportTaiwan getDescriptionReport() {
        return descriptionReport;
    }


    @Override
    public void setDescriptionReport(DescriptionReport  descriptionReport) {
        this.descriptionReport =(DescriptionReportTaiwan ) descriptionReport;
    }
}
