package com.example.ReportPlayer.controller.controller.api.comment;

import com.example.ReportPlayer.builder.comment.CommentBuilder;
import com.example.ReportPlayer.builder.comment.CommentDtoBuilder;
import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.comment.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    @Qualifier("comment_service_"+ Server.Region.EUW)
    private CommentService commentService;


    @Test
    @WithMockUser(username = "Apollo")
    public void formComment_ShouldReturnFormComment() throws Exception {
        //arrange
        //act
        mockMvc.perform(get("/api/comment/save-comment")).andExpect(status().isOk());
        //assert
    }

    @Test
    @WithMockUser(username = "Apollo")
    public void save_ShouldSave() throws Exception {
        //arrange
        final CommentDto commentDto = CommentDtoBuilder.newBuilder().comment("he was afk").report(new DescriptionReport()).user(new User()).region(Server.Region.EUW).build();
        final Comment comment = CommentBuilder.newBuilder().user(new User()).report(new DescriptionReport()).region(Server.Region.EUW).comment("he was afk").build();
        //act
        when(commentService.save(any(CommentDto.class))).thenReturn(comment);
        mockMvc.perform(post("/api/comment/save-comment").
                with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(commentDto))).
                andExpect(status().isOk()).
                andExpect(view().name("success")).
                andDo(print());
        //assert
        verify(commentService,times(1)).save(any(CommentDto.class));
    }

    @Test
    @WithMockUser(username = "Apollo")
    public void save_ShouldNotSaveIfCommentDtoIsInvalid() throws Exception {
        //arrange
        final CommentDto commentDto = CommentDtoBuilder.newBuilder().comment("").report(new DescriptionReport()).user(new User()).build();
        final Comment comment = CommentBuilder.newBuilder().user(new User()).report(new DescriptionReport()).region(Server.Region.EUW).comment("he was afk").build();
        //act
        when(commentService.save(any(CommentDto.class))).thenReturn(comment);
        mockMvc.perform(post("/api/comment/save-comment").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(commentDto))).
                andExpect(view().name("error")).
                andExpect(status().isOk());
        //assert
    }

    @Test
    @WithMockUser(username = "Apollo")
    public void delete_ShouldDeleteById() throws Exception {
        //arrange
        long id=1l;
        String region=Server.Region.EUW;
        //act
        doNothing().when(commentService).deleteById(anyLong());
        mockMvc.perform(get("/api/comment/delete-comment?id="+id+"&region="+region)).
                andExpect(status().isOk());
        verify(commentService,times(1)).deleteById(anyLong());
    }

    @Test
    @WithMockUser(username = "Apollo")
    public void edit_ShouldEditComment() throws Exception {
        //arrange
        final CommentDto commentDto = CommentDtoBuilder.newBuilder().id(1L).comment("he was afk").report(new DescriptionReport()).user(new User()).region(Server.Region.EUW).build();
        //act
        doNothing().when(commentService).editComment(any(CommentDto.class));
        mockMvc.perform(post("/api/comment/edit-comment").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(commentDto))).
                andExpect(status().isOk());
        //assert
        verify(commentService,times(1)).editComment(any(CommentDto.class));
    }

}
