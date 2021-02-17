package com.example.ReportPlayer.models.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportEuNordicEst;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.EU_NORDIC_AND_EST)
public class CommentEuNe extends Comment{

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_eu_nordic_est_id", nullable = false)
    private DescriptionReportEuNordicEst descriptionReport;

    public CommentEuNe() {}

    @Override
    public DescriptionReportEuNordicEst getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportEuNordicEst ) descriptionReport;
    }


}
