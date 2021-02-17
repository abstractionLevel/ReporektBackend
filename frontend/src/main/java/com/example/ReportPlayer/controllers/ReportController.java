package com.example.ReportPlayer.controllers;


import com.example.ReportPlayer.dto.report.DescriptionReportDto;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.report.ReportService;
import com.example.ReportPlayer.services.report.description.DescriptionReportService;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.user.UserService;
import com.example.ReportPlayer.dto.report.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/reports")
public class ReportController {


    private ReportService reportService;
    private PlayerService playerService;
    private DescriptionReportService descriptionReportService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ApplicationContext context;



    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody final ReportDto reportDto,@RequestParam("region") String region) {
        final CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findByUsername(userDetails.getUsername());
        reportService = (ReportService) context.getBean("report_service_"+region);
        reportDto.setRegion(region);
        reportDto.setUser(user);
        if(reportService.createReport(reportDto) != null) {
            return  ResponseEntity.ok().build();
        }
        return  ResponseEntity.noContent().build();
    }


    @DeleteMapping(path= "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id,@RequestParam("region") String region) {
        reportService = (ReportService) context.getBean("report_service_"+region);
        reportService.delete(id);
        return ResponseEntity.accepted().build();
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id,@RequestParam("region") String region) {
        reportService = (ReportService) context.getBean("report_service_"+region);
        final ReportDto reportDto = reportService.getReportById(id);
        return  ResponseEntity.ok(reportDto);
    }

    @GetMapping(path = "/last")
    public List<DescriptionReportDto> getLastReport(@RequestParam("region") String region) {
        List<DescriptionReportDto> descriptionReportDtos = new ArrayList<>();
        descriptionReportService = (DescriptionReportService) context.getBean("description_report_service_"+region);
        playerService = (PlayerService) context.getBean("player_service_"+region);
        for(DescriptionReport descriptionReport: descriptionReportService.getTheLast10DescriptionReport()) {
            Optional<User> user = userService.findById(descriptionReport.getUser().getId());
            User userExpected = user.get();
            Player player = playerService.findByPlayer(descriptionReport.getPlayer());
            DescriptionReportDto descriptionReportDto = new DescriptionReportDto(descriptionReport.getDescription(),
                    descriptionReport.getReportType(),descriptionReport.getDate(),descriptionReport.getId(),player.getNickname(),userExpected.getUsername());
            descriptionReportDtos.add(descriptionReportDto);
        }
        return descriptionReportDtos;
    }

    @PutMapping()
    public ResponseEntity<?> update( @Valid @RequestBody final ReportDto reportDto,@RequestParam("region") String region) {
        reportService = (ReportService) context.getBean("report_service_"+region);
        final ReportDto reportDtoExpected = reportService.getReportById(reportDto.getId());
        reportService.delete(reportDtoExpected.getId());
        return ResponseEntity.ok( reportService.createReport(reportDto));
    }

    @GetMapping("/players/{username}")
    public List<DescriptionReportDto> get( @PathVariable("username") String username,@RequestParam("region") String region) {
        playerService = (PlayerService) context.getBean("player_service_"+region);
        descriptionReportService = (DescriptionReportService) context.getBean("description_report_service_"+region);
        final Player player = playerService.findByNickname(username);
        List<DescriptionReportDto> reportDtos = new ArrayList<>();
        for(DescriptionReport descriptionReport: descriptionReportService.getDescriptionReportsByPlayerId(player.getId())) {
            Optional<User> user = userService.findById(descriptionReport.getUser().getId());
            User userExpected = user.get();
            DescriptionReportDto descriptionReportDto = new DescriptionReportDto(descriptionReport.getDescription(),descriptionReport.getReportType(),descriptionReport.getDate(),descriptionReport.getId(),player.getNickname(),userExpected.getUsername());
            reportDtos.add(descriptionReportDto);
        }
        return reportDtos;
    }

}
