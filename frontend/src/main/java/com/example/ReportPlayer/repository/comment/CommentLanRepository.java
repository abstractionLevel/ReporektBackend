package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentLan;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.LAN)
public interface CommentLanRepository  extends CommentBaseRepository<CommentLan>{
}
