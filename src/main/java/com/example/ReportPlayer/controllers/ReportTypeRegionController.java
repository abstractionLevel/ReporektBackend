package com.example.ReportPlayer.controllers;


import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import com.example.ReportPlayer.services.reportTypeRegion.ReportTypeRegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "https://reporekt.com","https://www.reporekt.com","http://localhost:3000"}, maxAge = 3600)
@RequestMapping("/api/v1/reportTypeRegion/")
public class ReportTypeRegionController {

    @Autowired
    public ReportTypeRegionServiceImpl reportTypeRegionService;

    @GetMapping(path = "/all")
    public List<ReportTypeRegion> getAllReportTypeByRegion(@RequestParam("region") String region) {
        return reportTypeRegionService.getAllReportTypeByRegion(region);
    }


}
