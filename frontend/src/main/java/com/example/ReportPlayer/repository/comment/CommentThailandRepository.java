package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentThailand;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.THAILAND)
public interface CommentThailandRepository  extends CommentBaseRepository<CommentThailand>{
}
