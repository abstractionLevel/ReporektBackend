package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentSingapore;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.SINGAPORE)
public interface CommentSingaporeRepository  extends CommentBaseRepository<CommentSingapore>{
}
