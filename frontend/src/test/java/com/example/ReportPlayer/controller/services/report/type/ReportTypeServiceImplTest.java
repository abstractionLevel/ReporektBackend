package com.example.ReportPlayer.controller.services.report.type;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import com.example.ReportPlayer.services.report.type.ReportTypeServiceImpl;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReportTypeServiceImplTest {

    @InjectMocks
    private ReportTypeServiceImpl service;
    @Mock
    private ReportTypeBaseRepository repository;


    @Test
    public void save_ShouldSaveReportType() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).build();
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(Server.Region.EUW).player(player).build();
        //act
        when(repository.save(reportType)).thenReturn(reportType);
        when(repository.findByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId())).thenReturn(null);
        final ReportType reportTypeExpected = service.save(reportType);
        //assert
        verify(repository,times(1)).save(reportType);
        verify(repository,times(1)).findByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId());
        assertNotNull(reportTypeExpected.getPlayer());
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.getReportType());
        assertEquals(0,reportTypeExpected.getCount());
    }

    @Test
    public void save_ShouldIncrementByOneIfReportTypeExist() throws Exception{
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).build();
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                count(1).region(Server.Region.EUW).player(player).build();
        //act
        when(repository.findByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId())).thenReturn(reportType);
        final ReportType reportTypeExpected = service.save(reportType);
        //assert
        verify(repository,times(0)).save(reportType);
        verify(repository,times(1)).findByReportTypeAndPlayerId(reportType.getReportType(),reportType.getPlayer().getId());
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.getReportType());
        assertEquals(2,reportTypeExpected.getCount());
    }

    @Test
    public void find_ShouldFindByReportType() throws Exception {
        //arrange
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(Server.Region.EUW).build();
        //act
        when(repository.findByReportType(reportType.getReportType())).thenReturn(reportType);
        final ReportType reportTypeExpected = service.findByReportType(reportType.getReportType());
        //assert
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.getReportType());
    }

    @Test
    public void find_ShouldFindByReportTypeByReportTypeAndPlayerId() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).id(1L).build();
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                region(Server.Region.EUW).player(player).build();
        //act
        when(repository.findByReportTypeAndPlayerId(anyString(),anyLong())).thenReturn(reportType);
        final ReportType reportTypeExpected = service.findByReportTypeAndPlayerId(reportType.getReportType(),player.getId());
        //assert
        assertNotNull(reportTypeExpected);
    }

    @Test
    public void delete_ShouldDeleteIfCountIsEqualToZero() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).build();
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                count(1).region(Server.Region.EUW).player(player).build();
        //act
        doNothing().when(repository).deleteByReportTypeAndPlayerId(anyString(),anyLong());
        when(repository.findByReportTypeAndPlayerId(anyString(),anyLong())).thenReturn(reportType);
        service.delete("",1);
        //assert
        verify(repository,times(1)).deleteByReportTypeAndPlayerId(anyString(),anyLong());
        verify(repository,times(1)).findByReportTypeAndPlayerId(anyString(),anyLong());
    }

    @Test
    public void delete_ShouldDecrementReportTypeCountIfCountIsMoreThanOne() throws Exception {
        //arrange
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                count(4).region(Server.Region.EUW).build();
        //act
        when(repository.findByReportTypeAndPlayerId(anyString(),anyLong())).thenReturn(reportType);
        service.delete("",1);
        //assert
        verify(repository,times(0)).deleteByReportTypeAndPlayerId(anyString(),anyLong());
        verify(repository,times(1)).findByReportTypeAndPlayerId(anyString(),anyLong());
        assertEquals(3,reportType.getCount());
    }

    @Test
    public void find_ShouldFindReportTypeByPlayer() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).build();
        final ReportType reportType = ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).
                count(1).region(Server.Region.EUW).player(player).build();
        //act
        when(repository.findReportTypeByPlayer(any(Player.class))).thenReturn(reportType);
        final ReportType reportTypeExpected = service.findReportTypeByPlayer(player);
        //assert
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.getReportType());
        assertEquals(player,reportTypeExpected.getPlayer());
    }


    @Test
    public void find_ShouldFindAllReportTypeByPlayerId() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").region(Server.Region.EUW).build();
        final List<ReportType> reportTypes = new ArrayList<>();
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(10).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).build());
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(1).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.CHEATING.toString()).build());
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(3).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.NEGATIVE_ATTITUDE.toString()).build());
        reportTypes.add(ReportTypeBuilder.newBuilder().player(player).count(4).region(Server.Region.EUW).reportType(com.example.ReportPlayer.enumerated.ReportType.HATE_SPEECH.toString()).build());
        //act
        when(repository.findAllReportTypeByPlayerId((anyLong()))).thenReturn(reportTypes);
        final List<ReportType> reportTypeExpected =  service.findAllReportTypeByPlayerId(player.getId());
        //assert
        assertNotNull(reportTypeExpected);
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.get(0));
    }
}
