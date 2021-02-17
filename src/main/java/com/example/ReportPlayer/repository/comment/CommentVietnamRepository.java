package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentVietnam;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.VIETNAM)
public interface CommentVietnamRepository extends CommentBaseRepository<CommentVietnam>{
}
