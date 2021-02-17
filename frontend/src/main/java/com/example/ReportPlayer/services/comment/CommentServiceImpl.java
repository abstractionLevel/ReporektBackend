package com.example.ReportPlayer.services.comment;

import com.example.ReportPlayer.builder.comment.CommentBuilder;
import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.exception.EntityNotExistException;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.repository.comment.CommentBaseRepository;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private CommentBaseRepository repository;

    public CommentServiceImpl(CommentBaseRepository repository) {
        this.repository = repository;
    }
    @Override
    public Comment save(CommentDto commentDto) {
        final Comment comment = CommentBuilder.newBuilder().region(commentDto.getRegion()).
                comment(commentDto.getComment()).report(commentDto.getDescriptionReport()).user(commentDto.getUser()).build();
        return (Comment) repository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Comment editComment(CommentDto commentDto) {
        final Comment comment = findById(commentDto.getId());
        comment.setComment(commentDto.getComment());
        return (Comment) repository.save(comment);
    }

    @Override
    public Comment findById(long id) {
        final Optional<Comment> commentExpected = repository.findById(id);
        if(!commentExpected.isPresent()) {
            throw new  EntityNotExistException("comment not exist");
        }
        return commentExpected.get();
    }
}
