package com.example.ReportPlayer.controllers;

import com.example.ReportPlayer.dto.report.ReportTypeDto;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.services.reportTypeRegion.ReportTypeRegionService;
import com.example.ReportPlayer.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportType/")
@CrossOrigin
public class ReportTypeController {

    ReportTypeService reportTypeService;
    ReportTypeRegionService reportTypeRegionService;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public List<ReportTypeDto> get(@RequestParam("region") String region) {
        reportTypeService = (ReportTypeService) context.getBean("report_type_service_"+region);
        List<ReportTypeDto> reportTypeDtos = new ArrayList<>();
        for(ReportType reportType : reportTypeService.findAll()) {
            ReportTypeDto reportTypeDto =  new ReportTypeDto(reportType.getReportType(),reportType.getCount());
            reportTypeDtos.add(reportTypeDto);
        }
        return reportTypeDtos;
    }

    @GetMapping(path = "/regions")
    public List<ReportTypeDto> getReportsTypeRegion(@RequestParam("region") String region) {
        reportTypeRegionService = (ReportTypeRegionService) context.getBean("report_type_region_service_"+region);
        List<ReportTypeDto> reportTypeDtos = new ArrayList<>();
        for(ReportTypeRegion reportType : reportTypeRegionService.getAllReportType()) {
            ReportTypeDto reportTypeDto =  new ReportTypeDto(reportType.getReportType(),reportType.getCount());
            reportTypeDtos.add(reportTypeDto);
        }
        return reportTypeDtos;
    }
}
