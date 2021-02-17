package com.example.ReportPlayer.controller.services.comment;

import com.example.ReportPlayer.builder.comment.CommentBuilder;
import com.example.ReportPlayer.builder.comment.CommentDtoBuilder;
import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.EntityNotExistException;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportEuw;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.repository.comment.CommentBaseRepository;
import com.example.ReportPlayer.services.comment.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CommentServiceImplTest {

    @InjectMocks
    @Qualifier("comment_service_"+ Server.Region.EUW)
    private CommentServiceImpl service;
    @Mock
    private CommentBaseRepository repository;

    @Test
    public void save_shouldSave() throws Exception {
        //arrange
        final CommentDto commentDto = CommentDtoBuilder.newBuilder().comment("he was afk").report(new DescriptionReport()).user(new User()).region(Server.Region.EUW).build();
        final Comment comment = CommentBuilder.newBuilder().user(commentDto.getUser()).report(commentDto.getDescriptionReport()).region(Server.Region.EUW).comment(commentDto.getComment()).build();
        //act
        when(repository.save(any(Comment.class))).thenReturn(comment);
        final Comment commentExpected = service.save(commentDto);
        //assert
        assertEquals("he was afk",commentExpected.getComment());
        verify(repository,times(1)).save(any(Comment.class));
    }

    @Test
    public void delete_ShouldDelete() throws Exception {
        //arrange
        //act
        doNothing().when(repository).deleteById(anyLong());
        service.deleteById(1L);
        //assert
        verify(repository,times(1)).deleteById(anyLong());
    }

    @Test
    public void find_ShouldFindById() throws Exception {
        //arrange
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Comment()));
        //act
        final Comment commentExpected = service.findById(1L);
        //assert
        assertNotNull(commentExpected);
    }

    @Test
    public void find_ShouldReturnExceptionWhenCommentIsNull() throws Exception {
        //arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        //act
        assertThrows(EntityNotExistException.class,() -> {
            service.findById(1L);
        });
    }

    @Test
    public void edit_ShouldEditComment() throws Exception {
        //arrange
        final CommentDto commentDto = CommentDtoBuilder.newBuilder().comment("he was afk").id(1L).report(new DescriptionReportEuw()).user(new User()).region(Server.Region.EUW).build();
        final Comment comment = CommentBuilder.newBuilder().user(commentDto.getUser()).report(commentDto.getDescriptionReport()).region(Server.Region.EUW).comment(commentDto.getComment()).build();
        //act
        when(repository.save(any(Comment.class))).thenReturn(comment);
        when(repository.findById(anyLong())).thenReturn(Optional.of(comment));
        final Comment commentExpected = service.editComment(commentDto);
        //assert
        assertEquals("he was afk",commentExpected.getComment());
        verify(repository,times(1)).save(any(Comment.class));
    }
}
