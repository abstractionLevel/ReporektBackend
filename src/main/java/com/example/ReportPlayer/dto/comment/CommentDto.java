package com.example.ReportPlayer.dto.comment;

import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;

import javax.validation.constraints.NotBlank;

public class CommentDto {

    @NotBlank
    private String comment;
    private User user;
    private String region;
    private DescriptionReport descriptionReport;
    private long id;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public DescriptionReport getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(DescriptionReport descriptionReport) {
        this.descriptionReport = descriptionReport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
