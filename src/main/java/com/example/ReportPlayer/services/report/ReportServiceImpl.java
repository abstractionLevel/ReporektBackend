package com.example.ReportPlayer.services.report;


import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.report.description.DescriptionReportService;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.builder.ReportDtoBuilder;
import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import com.example.ReportPlayer.dto.report.ReportDto;
import com.example.ReportPlayer.services.reportTypeRegion.ReportTypeRegionService;
import com.example.ReportPlayer.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private PlayerService playerService;
    private DescriptionReportService descriptionReportService;
    private ReportTypeService reportTypeService;
    @Autowired
    private ReportTypeRegionService reportTypeRegionService;

    public ReportServiceImpl(PlayerService playerService,DescriptionReportService descriptionReportService, ReportTypeService reportTypeService) {
        this.playerService = playerService;
        this.descriptionReportService = descriptionReportService;
        this.reportTypeService = reportTypeService;
    }


    @Override
    public ReportDto createReport(ReportDto reportDto) {
        final Player player = playerService.save(PlayerBuilder.newBuilder().region(reportDto.getRegion()).username(reportDto.getNickname()).build());
        final DescriptionReport descriptionReport = descriptionReportService.save( DescriptionReportBuilder.newBuilder().region(reportDto.getRegion()).user(reportDto.getUser()).
                description(reportDto.getDescription()).reportType(reportDto.getReportType()).player(player).build());
        final ReportType reportType = reportTypeService.save(ReportTypeBuilder.newBuilder().region(reportDto.getRegion()).player(player).reportType(reportDto.getReportType()).build());
        reportTypeRegionService.save(new ReportTypeRegion(reportType.getReportType(),1,reportDto.getRegion()));//test
        return ReportDtoBuilder.newBuilder().description(descriptionReport.getDescription()).
                nickname(player.getNickname()).reportType(reportType.getReportType()).region(reportDto.getRegion()).user(reportDto.getUser()).build();
    }

    @Override
    public void delete(long idDescriptionReport) {
        DescriptionReport descriptionReportExpected = descriptionReportService.findById(idDescriptionReport);
        descriptionReportService.deleteById(idDescriptionReport);
        playerService.delete(descriptionReportExpected.getPlayer().getId());
        reportTypeService.delete(descriptionReportExpected.getReportType(),descriptionReportExpected.getPlayer().getId());
    }

    @Override
    public ReportDto getReportById(long idDescriptionReport) {
        final DescriptionReport descriptionReport = descriptionReportService.findById(idDescriptionReport);
        final Player player = playerService.findByNickname(descriptionReport.getPlayer().getNickname());
        final ReportType reportType = reportTypeService.findByReportTypeAndPlayerId(descriptionReport.getReportType(),player.getId());
        return ReportDtoBuilder.newBuilder().nickname(player.getNickname()).
                reportType(reportType.getReportType()).description(descriptionReport.getDescription()).build();
    }

    @Override
    public List<ReportDto> getPaginationDescription(long playerId) {
        return null;
    }


}
