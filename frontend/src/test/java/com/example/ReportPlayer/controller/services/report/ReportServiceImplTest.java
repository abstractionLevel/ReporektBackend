package com.example.ReportPlayer.controller.services.report;

import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.report.ReportServiceImpl;
import com.example.ReportPlayer.services.report.description.DescriptionReportService;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.builder.ReportDtoBuilder;
import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.dto.report.ReportDto;
import com.example.ReportPlayer.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReportServiceImplTest {

    @InjectMocks
    @Qualifier("report_service_"+Server.Region.EUW)
    private ReportServiceImpl reportService;
    @Mock
    private PlayerService playerService;
    @Mock
    private DescriptionReportService descriptionReportService;
    @Mock
    private ReportTypeService reportTypeService;



    @Test
    public void createReport_ShouldCreateReport() throws Exception {

        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final String reportTypeReport = com.example.ReportPlayer.enumerated.ReportType.AFK.toString();
        final Player player =  PlayerBuilder.newBuilder().username("Apollo").region(Server.Region.EUW).build();
        final DescriptionReport descriptionReport =  DescriptionReportBuilder.newBuilder().user(user).region(Server.Region.EUW).description("it was afk").reportType(reportTypeReport).build();
        final ReportType reportType =  ReportTypeBuilder.newBuilder().reportType(reportTypeReport).region(Server.Region.EUW).build();
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().region(Server.Region.EUW).description("it was afk").user(user).nickname("Apollo").reportType(reportTypeReport).build();
        //act
        when(playerService.save(any(Player.class))).thenReturn(player);
        when(descriptionReportService.save(any(DescriptionReport.class))).thenReturn(descriptionReport);
        when(reportTypeService.save(any(ReportType.class))).thenReturn(reportType);
        final ReportDto reportDtoExpected = reportService.createReport(reportDto);
        //assert
        verify(playerService,times(1)).save(any(Player.class));
        verify(descriptionReportService
                ,times(1)).save(any(DescriptionReport.class));
        verify(reportTypeService,times(1)).save(any(ReportType.class));

        assertEquals(reportDto.getClass().getSimpleName(),reportDtoExpected.getClass().getSimpleName());
        assertEquals("it was afk",reportDtoExpected.getDescription());
        assertEquals(reportTypeReport,reportDtoExpected.getReportType());
        assertEquals("Apollo",reportDtoExpected.getNickname());
        assertEquals(Server.Region.EUW,reportDtoExpected.getRegion());
        assertEquals(user,reportDtoExpected.getUser());
    }


    @Test
    public void delete_ShouldDeleteReport() {
        //assert
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo1").build();
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().description("it was afk").region(Server.Region.EUW).
                player(player).reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).build();
        //act
        when(playerService.delete(anyLong())).thenReturn(null);
        when(descriptionReportService.findById(anyLong())).thenReturn(descriptionReport);
        doNothing().when(reportTypeService).delete(anyString(),anyLong());
        reportService.delete(1L);
        //assert
        verify(playerService,times(1)).delete(anyLong());
        verify(reportTypeService,times(1)).delete(anyString(),anyLong());
        verify(descriptionReportService,times(1)).deleteById(anyLong());
        verify(descriptionReportService,times(1)).findById(anyLong());
    }

    @Test
    public void fetch_ShouldFetchReport() {
        //arrange
        long idDescriptionReport=1L;
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo1").build();
        final DescriptionReport descriptionReport = DescriptionReportBuilder.newBuilder().description("it was afk").region(Server.Region.EUW).
                player(player).reportType(com.example.ReportPlayer.enumerated.ReportType.AFK.toString()).build();
        final ReportType reportType =  ReportTypeBuilder.newBuilder().reportType(descriptionReport.getReportType()).player(player).region(Server.Region.EUW).build();
        //act
        when(descriptionReportService.findById(anyLong())).thenReturn(descriptionReport);
        when(playerService.findByNickname(anyString())).thenReturn(player);
        when(reportTypeService.findByReportTypeAndPlayerId(anyString(),anyLong())).thenReturn(reportType);
        final ReportDto reportDtoExpected = reportService.getReportById(idDescriptionReport);
        //assert
        verify(descriptionReportService,times(1)).findById(anyLong());
        verify(playerService,times(1)).findByNickname(anyString());
        verify(reportTypeService,times(1)).findByReportTypeAndPlayerId(anyString(),anyLong());
        assertNotNull(reportDtoExpected);
    }


}
