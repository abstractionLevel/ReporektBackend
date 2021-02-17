package com.example.ReportPlayer.builder.comment;

import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;

public class CommentDtoBuilder {

    private String comment;
    private DescriptionReport descriptionReport;
    private User user;
    private String region;
    private long id;

    private CommentDtoBuilder() {}

    public static CommentDtoBuilder newBuilder() { return new CommentDtoBuilder();}

    public CommentDtoBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentDtoBuilder region(String region) {
        this.region = region;
        return this;
    }
    public CommentDtoBuilder report(DescriptionReport descriptionReport) {
        this.descriptionReport= descriptionReport;
        return this;
    }

    public CommentDtoBuilder user(User user) {
        this.user = user;
        return this;
    }

    public CommentDtoBuilder id(long id) {
        this.id = id;
        return this;
    }

    public CommentDto build() {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment);
        commentDto.setDescriptionReport(descriptionReport);
        commentDto.setUser(user);
        commentDto.setRegion(region);
        commentDto.setId(id);
        return commentDto;
    }
}
