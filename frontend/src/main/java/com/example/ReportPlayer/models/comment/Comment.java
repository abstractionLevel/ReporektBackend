package com.example.ReportPlayer.models.comment;

import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.user.User;

import javax.persistence.*;


@MappedSuperclass
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    private DescriptionReport descriptionReport;

    public Comment() {}
    public Comment(String comment, User user, DescriptionReport descriptionReport) {
        this.comment = comment;
        this.user = user;
        this.descriptionReport = descriptionReport;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public DescriptionReport getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = descriptionReport;
    }
}
