package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentJapan;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.JAPAN)
public interface CommentJapanRepository extends CommentBaseRepository<CommentJapan>{
}
