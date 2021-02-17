package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentLas;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.LAS)
public interface CommentLasRepository extends CommentBaseRepository<CommentLas>{
}
