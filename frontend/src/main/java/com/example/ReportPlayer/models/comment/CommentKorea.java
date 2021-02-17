package com.example.ReportPlayer.models.comment;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportKorea;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.KOREA)
public class CommentKorea extends Comment{

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_korea_id", nullable = false)
    private DescriptionReportKorea descriptionReport;

    public CommentKorea() {}

    @Override
    public DescriptionReportKorea getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportKorea )descriptionReport;
    }
}
