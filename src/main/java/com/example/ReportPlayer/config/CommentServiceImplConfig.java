package com.example.ReportPlayer.config;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.repository.comment.CommentBaseRepository;
import com.example.ReportPlayer.services.comment.CommentService;
import com.example.ReportPlayer.services.comment.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentServiceImplConfig {

    @Bean("comment_service_"+ Server.Region.EUW)
    public CommentService getCommentEuw(@Qualifier("comment_repository_"+ Server.Region.EUW) CommentBaseRepository repository) {
        return new CommentServiceImpl(repository);
    }

    @Bean("comment_service_"+ Server.Region.NORTH_AMERICA)
    public CommentService getCommentNa(@Qualifier("comment_repository_"+ Server.Region.NORTH_AMERICA)CommentBaseRepository repository) {
        return new CommentServiceImpl(repository);
    }
}
