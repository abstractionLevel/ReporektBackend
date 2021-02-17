package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentEuNe;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.EU_NORDIC_AND_EST)
public interface CommentEuNeRepository extends CommentBaseRepository<CommentEuNe> {


}
