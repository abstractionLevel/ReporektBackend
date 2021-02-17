package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentPhilippines;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.PHILIPPINES)
public interface CommentPhilippinesRepository extends CommentBaseRepository<CommentPhilippines>{
}
