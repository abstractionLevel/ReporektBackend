package com.example.ReportPlayer.models.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportBrazil;
import com.example.ReportPlayer.models.user.User;


import javax.persistence.*;

@Entity
@Table(name = "comment_"+ Server.Region.BRAZIL)
public class CommentBrazil extends Comment {

    @ManyToOne(fetch = FetchType.EAGER, optional=false , cascade = CascadeType.ALL)
    @JoinColumn(name = "description_report_brazil_id", nullable = false)
    private DescriptionReportBrazil descriptionReport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CommentBrazil() {}

    @Override
    public DescriptionReportBrazil getDescriptionReport() {
        return descriptionReport;
    }

    @Override
    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = (DescriptionReportBrazil) descriptionReport;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user =  user;
    }
}
