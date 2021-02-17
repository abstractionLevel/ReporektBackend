package com.example.ReportPlayer.services.reportTypeRegion;

import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import com.example.ReportPlayer.repository.reportTypeOfRegion.ReportTypeRegionBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportTypeRegionServiceImpl implements ReportTypeRegionService{

    @Autowired
    private ReportTypeRegionBaseRepository repository;


    @Override
    public List<ReportTypeRegion> getAllReportType() {
        return repository.findAll();
    }

    @Override
    public List<ReportTypeRegion> getAllReportTypeByRegion(String region) {
        return repository.findByRegion(region);
    }

    @Override
    public ReportTypeRegion save(ReportTypeRegion reportTypeRegion) {
        ReportTypeRegion reportTypeRegionExpected = repository.findByReportTypeAndRegion(reportTypeRegion.getReportType(),reportTypeRegion.getRegion());
        if(reportTypeRegionExpected!=null) {
            reportTypeRegionExpected.setCount(reportTypeRegionExpected.getCount()+1);
            return repository.save(reportTypeRegionExpected);

        }
        return repository.save(reportTypeRegion);
    }
}
