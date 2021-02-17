package com.example.ReportPlayer.builder.comment;

import com.example.ReportPlayer.factory.comment.CommentFactory;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.user.User;

public class CommentBuilder {

    private String comment;
    private DescriptionReport  descriptionReport;
    private User user;
    private String region;

    private CommentBuilder() {}

    public static CommentBuilder newBuilder() { return new CommentBuilder();}

    public CommentBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentBuilder region(String region) {
        this.region = region;
        return this;
    }

    public CommentBuilder report(DescriptionReport descriptionReport) {
        this.descriptionReport = descriptionReport;
        return this;
    }

    public CommentBuilder user(User user) {
        this.user = user;
        return this;
    }

    public Comment build() {
        Comment commentObj = CommentFactory.getComment(region);
        commentObj.setComment(comment);
        commentObj.setDescriptionReport(descriptionReport);
        commentObj.setUser(user);
        return commentObj;
    }
}
