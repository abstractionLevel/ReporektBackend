package com.example.ReportPlayer.repository.reportTypeOfRegion;

import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportTypeRegionBaseRepository extends JpaRepository<ReportTypeRegion,Long> {
    List<ReportTypeRegion> findByRegion(String region);
    ReportTypeRegion findByReportTypeAndRegion(String reportType, String region);
}
