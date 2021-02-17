package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentOceania;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.OCEANIA)
public interface CommentOceaniaRepository extends CommentBaseRepository<CommentOceania>{
}
