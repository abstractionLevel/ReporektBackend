package com.example.ReportPlayer.repository.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.CommentIndonesia;
import org.springframework.stereotype.Repository;

@Repository("comment_repository_"+ Server.Region.INDONESIA)
public interface CommentIndonesiaRepository extends CommentBaseRepository<CommentIndonesia>{
}
