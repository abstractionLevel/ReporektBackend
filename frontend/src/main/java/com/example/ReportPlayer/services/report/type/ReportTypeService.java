package com.example.ReportPlayer.services.report.type;


import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;

import java.util.List;

public interface ReportTypeService {

    ReportType save(ReportType reportType);
    ReportType findByReportType(String reportType);
    void delete(String reportType,long idPlayer);
    ReportType findByReportTypeAndPlayerId(String reportType, long playerId);
    ReportType findReportTypeByPlayer(Player player);
    List<ReportType> findAllReportTypeByPlayerId(long id);
    List<ReportType> findAll();

}
