package com.example.ReportPlayer.repository.report.description;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


@NoRepositoryBean
public interface DescriptionReportBaseRepository<T> extends JpaRepository<T,Long> {
    T findByDescription(String s);
    List<T> findAllByPlayerId(long id);
    List<T> findTop10ByOrderByDateDesc();

}
