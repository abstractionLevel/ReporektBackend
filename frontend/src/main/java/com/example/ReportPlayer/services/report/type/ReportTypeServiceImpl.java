package com.example.ReportPlayer.services.report.type;

import com.example.ReportPlayer.exception.EntityNotExistException;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;

import java.util.ArrayList;
import java.util.List;

public class ReportTypeServiceImpl implements ReportTypeService {

    private ReportTypeBaseRepository repository;

    public ReportTypeServiceImpl(ReportTypeBaseRepository repository) {
        this.repository = repository;
    }


    @Override
    public ReportType save(ReportType reportType) {
        final ReportType reportTypeExpected = (ReportType) repository.findByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId());
        if(reportTypeExpected!=null) {
            reportTypeExpected.setCount(reportTypeExpected.getCount() + 1);
            return (ReportType) repository.save(reportTypeExpected);
        }
        reportType.setCount(1);
        return (ReportType) repository.save(reportType);
    }

    @Override
    public ReportType findByReportType(String reportType) {
        return (ReportType) repository.findByReportType(reportType);
    }



    @Override
    public void delete(String reportType, long idPlayer) {
        final ReportType reportTypeExpected = (ReportType) repository.findByReportTypeAndPlayerId(reportType,idPlayer);
        if(reportTypeExpected.getCount()>1) {
            reportTypeExpected.setCount(reportTypeExpected.getCount() - 1);
            repository.save(reportTypeExpected);
        }
        else {
            repository.deleteByReportTypeAndPlayerId(reportType,idPlayer);
        }

    }

    @Override
    public ReportType findByReportTypeAndPlayerId(String reportType, long playerId) {
        final ReportType reportTypeExpected = (ReportType) repository.findByReportTypeAndPlayerId(reportType,playerId);
        if (reportType == null) {
            throw new EntityNotExistException("report type doesn't exist");
        }
        return reportTypeExpected;
    }

    @Override
    public ReportType findReportTypeByPlayer(Player player) {
        return (ReportType) repository.findReportTypeByPlayer(player);
    }

    @Override
    public List<ReportType> findAllReportTypeByPlayerId(long id) {
        List<ReportType> reportTypes = repository.findAllReportTypeByPlayerId(id);
        return  reportTypes;
    }

    @Override
    public List<ReportType> findAll() {
        return repository.findAll();
    }


}
