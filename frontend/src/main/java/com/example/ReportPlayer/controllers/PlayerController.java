package com.example.ReportPlayer.controllers;


import com.example.ReportPlayer.builder.report.player.TopPlayerDtoBuilder;
import com.example.ReportPlayer.dto.report.PlayerDto;
import com.example.ReportPlayer.dto.report.ReportTypeDto;
import com.example.ReportPlayer.dto.report.TopPlayerDto;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/players")
public class PlayerController {

    private PlayerService playerService;
    private ReportTypeService reportTypeService;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserService userService;

    @GetMapping("/top")
    public List<TopPlayerDto> getTopPlayer(@RequestParam("region") String region) {
        playerService = (PlayerService) context.getBean("player_service_"+ region);
        reportTypeService = (ReportTypeService) context.getBean("report_type_service_"+ region);
        List<Player> players = playerService.getTopPlayers();
        List<TopPlayerDto> playerDtos = new ArrayList<>();
        for(Player player: players) {
            List<String> reportTypes  = new ArrayList<>();
            for (ReportType repType : reportTypeService.findAllReportTypeByPlayerId(player.getId())) {
                reportTypes.add(repType.getReportType());
            }
            TopPlayerDto topPlayerDto = TopPlayerDtoBuilder.newBuilder().nickname(player.getNickname()).playerId(player.getId()).
                    reportCount(player.getReportCount()).reportType(reportTypes).date(player.getDate()).build();
            playerDtos.add(topPlayerDto);

        }
        return playerDtos;
    }

    @GetMapping("/latest")
    public List<PlayerDto> lastPlayerReported(@RequestParam("region") String region) {
        playerService = (PlayerService) context.getBean("player_service_"+region);
        List<PlayerDto> playerList = new ArrayList<>();
        for (Player player: playerService.getLastReportedPlayers()) {
            PlayerDto playerDto = new PlayerDto(player.getNickname());
            playerList.add(playerDto);
        }
        return playerList;
    }

    @GetMapping("/reportsType")
    public List<ReportTypeDto> getReportsTypeOfPlayer(@RequestParam("username") String  username,@RequestParam("region") String region) {
        reportTypeService = (ReportTypeService) context.getBean("report_type_service_"+region);
        playerService = (PlayerService) context.getBean("player_service_"+region);
        Player player = playerService.findByNickname(username);
        List<ReportTypeDto> reportTypes = new ArrayList<>();
        for(ReportType reportType : reportTypeService.findAllReportTypeByPlayerId(player.getId())) {
            ReportTypeDto reportTypeDto = new ReportTypeDto(reportType.getReportType(),reportType.getCount());
            reportTypes.add(reportTypeDto);
        }
        return reportTypes;
    }

    @GetMapping("/search")
    public PlayerDto findPlayer(@RequestParam("username")String username,@RequestParam("region") String region) {
        playerService = (PlayerService) context.getBean("player_service_"+region);
        Player player = playerService.findByNickname(username);
        if(player==null)
            return null;
        PlayerDto playerDto = new PlayerDto(player.getNickname());

        return playerDto;

    }
}
