package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentBrazil;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.BRAZIL)
public interface CommentBrazilRepository extends CommentBaseRepository<CommentBrazil>{
}
