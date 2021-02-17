package com.example.ReportPlayer.models.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportNa;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.NORTH_AMERICA)
public class CommentNa extends Comment {


    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_na_id", nullable = false)
    private DescriptionReportNa descriptionReport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public User getUser() {
        return user;
    }

    public CommentNa() {

    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public DescriptionReportNa getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportNa )descriptionReport;
    }
}
