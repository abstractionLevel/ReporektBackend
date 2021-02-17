package com.example.ReportPlayer.repository.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface  ReportTypeBaseRepository<T> extends JpaRepository<T,Long>{
    T findByReportType(T t);
    T findReportTypeByPlayer(T t);
    T findByReportTypeAndPlayerId(String s, long id);
    List<T> findAllReportTypeByPlayerId(long id);
    void deleteByReportTypeAndPlayerId(String s, long id);
}
