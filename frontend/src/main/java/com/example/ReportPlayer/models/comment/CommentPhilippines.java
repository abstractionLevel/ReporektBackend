package com.example.ReportPlayer.models.comment;


import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportPhilippines;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.PHILIPPINES)
public class CommentPhilippines extends Comment {

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "description_report_philippines_id", nullable = false)
    private DescriptionReportPhilippines descriptionReport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public CommentPhilippines() {}

    @Override
    public DescriptionReportPhilippines getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportPhilippines )descriptionReport;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user  =  user;
    }
}
