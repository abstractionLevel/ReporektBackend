package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentTurkey;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.TURKEY)
public interface CommentTurkeyRepository extends CommentBaseRepository<CommentTurkey>{
}
