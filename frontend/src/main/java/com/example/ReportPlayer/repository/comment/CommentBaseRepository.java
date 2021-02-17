package com.example.ReportPlayer.repository.comment;


import com.example.ReportPlayer.models.comment.Comment;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CommentBaseRepository<T> extends JpaRepository<T,Long>{

    T findByDescriptionReport(DescriptionReport descriptionReport);
    T findByComment(String c);
}
