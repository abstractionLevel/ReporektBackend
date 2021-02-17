package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentTaiwan;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.TAIWAN)
public interface CommentTaiwanRepository extends CommentBaseRepository<CommentTaiwan>{
}
