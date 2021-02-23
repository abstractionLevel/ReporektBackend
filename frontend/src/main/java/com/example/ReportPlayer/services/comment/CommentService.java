package com.example.ReportPlayer.services.comment;

import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.models.comment.Comment;

import javax.validation.Valid;

public interface CommentService {

    Comment save(CommentDto commentDto);

    void deleteById(long id);

    Comment findById(long id);

    Comment editComment(CommentDto commentDto);
}
