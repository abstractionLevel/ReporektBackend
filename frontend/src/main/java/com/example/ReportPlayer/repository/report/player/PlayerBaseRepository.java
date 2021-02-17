package com.example.ReportPlayer.repository.report.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PlayerBaseRepository<T> extends JpaRepository<T,Long> {

    T findByNickname(String s);
    List<T> findTop5ByOrderByReportCountDesc();
    List<T> findTop3ByOrderByDateDesc();

}
