package com.example.ReportPlayer.controllers;

import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.comment.CommentNa;
import com.example.ReportPlayer.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller()
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private ApplicationContext applicationContext;

    private CommentService commentService;

    @GetMapping(value = "/save-comment")
    public ModelAndView showForm(ModelAndView modelAndView) {
        modelAndView.setViewName("form-comment");
        return modelAndView;
    }

    @PostMapping(value = "/save-comment")
    public ModelAndView saveComment(ModelAndView modelAndView, @Valid @RequestBody CommentDto commentDto, BindingResult result) {
        if(result.hasErrors()) {
            modelAndView.setViewName("error");
            return modelAndView;
        }
        commentService = (CommentService) applicationContext.getBean("comment_service_"+commentDto.getRegion());
        commentService.save(commentDto);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @GetMapping(value = "/delete-comment")
    public ModelAndView deleteComment(ModelAndView modelAndView, @RequestParam("id") long id, @RequestParam("region") String region) {
        modelAndView.setViewName("success");
        commentService = (CommentService) applicationContext.getBean("comment_service_"+region);
        commentService.deleteById(id);
        return modelAndView;
    }

    @PostMapping(value = "/edit-comment")
    public ModelAndView editComment(ModelAndView modelAndView, @Valid @RequestBody CommentDto commentDto, BindingResult result) {
        modelAndView.setViewName("success");
        commentService = (CommentService) applicationContext.getBean("comment_service_"+commentDto.getRegion());
        commentService.editComment(commentDto);
        return modelAndView;
    }
}
