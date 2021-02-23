package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentNa;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.NORTH_AMERICA)
public interface CommentNaRepository extends CommentBaseRepository<CommentNa> {
}
