package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentRussia;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.RUSSIA)
public interface CommentRussiaRepository extends CommentBaseRepository<CommentRussia>{
}
