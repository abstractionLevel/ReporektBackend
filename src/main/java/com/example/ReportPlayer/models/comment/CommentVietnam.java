package com.example.ReportPlayer.models.comment;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportVietnam;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.VIETNAM)
public class CommentVietnam extends Comment {

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_vietnam_id", nullable = false)
    private DescriptionReportVietnam descriptionReport;

    public CommentVietnam() {}

    @Override
    public DescriptionReportVietnam getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportVietnam )descriptionReport;
    }
}
