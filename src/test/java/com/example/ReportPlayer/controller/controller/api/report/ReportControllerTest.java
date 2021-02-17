package com.example.ReportPlayer.controller.controller.api.report;

import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.security.CustomUserDetails;
import com.example.ReportPlayer.services.report.ReportService;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.player.PlayerServiceImpl;
import com.example.ReportPlayer.services.user.UserService;
import com.example.ReportPlayer.builder.ReportDtoBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.dto.report.ReportDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static com.example.ReportPlayer.controller.controller.api.JwtToken.TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    @Qualifier("report_service_"+Server.Region.EUW)
    private ReportService reportService;
    @MockBean
    @Qualifier("player_service_"+Server.Region.EUW)
    private PlayerService playerService;
    private CustomUserDetails userDetails;

    private String HEADER = "Bearer";
    private String JWT = TOKEN;


    @Test
    public void save_ShouldSaveReport() throws Exception {
        //arrange

        final ReportDto reportDto = ReportDtoBuilder.newBuilder().user(new User()).region("euuw").
                nickname("Apollo12").description("it was afk").reportType(ReportType.AFK.toString()).build();
        //act
        when(reportService.createReport(any(ReportDto.class))).thenReturn(reportDto);
        when(userService.findByUsername(anyString())).thenReturn(UserBuilder.newBuilder().username("df").build());
        mockMvc.perform(post("/api/v1/reports").
                contentType(MediaType.APPLICATION_JSON).
                header(HEADER,JWT).
                content(objectMapper.writeValueAsString(reportDto))).
                andExpect(status().isOk()).
                andExpect(jsonPath("nickname").value(reportDto.getNickname())).
                andExpect(jsonPath("reportType").value(reportDto.getReportType())).
                andExpect(jsonPath("description").value(reportDto.getDescription())).
                andDo(print()).
                andReturn();
        //assert
        verify(reportService,times(1)).createReport(any(ReportDto.class));
        verify(userService,times(1)).findByUsername(anyString());
    }



    @Test
    public void save_ReturnErrorWhenReportTypeIsBlank() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("").build();
        //act
        mockMvc.perform(post("/api/v1/reports/").
                contentType(MediaType.APPLICATION_JSON).
                header(HEADER,JWT).
                content(objectMapper.writeValueAsString(reportDto))).
                andExpect(status().isBadRequest()).
                andDo(print());
        //assert
        verify(reportService,times(0)).createReport(any(ReportDto.class));
    }



    @Test
    public void delete_ShouldReport() throws Exception {
        //arrange
        final User  user = UserBuilder.newBuilder().username("Apollo12").isActive(true).password("Tricolore88").confirmPassword("Tricolore88").
                email("Apollo12").build();
        //act
        doNothing().when(reportService).delete(anyLong());
        when(userService.findByUsername(anyString())).thenReturn(user);
        long idDescriptionReport = 1L;
        mockMvc.perform(delete("/api/v1/reports/"+idDescriptionReport).
                header(HEADER,JWT)).
                andDo(print()).
                andExpect(status().isAccepted()).
                andDo(print());
        //assert
        verify(userService,times(1)).findByUsername(anyString());
        verify(reportService,times(1)).delete(anyLong());
    }

    @Test
    public void findById_ShouldGetReport() throws Exception {
        //arrange
        final User  user = UserBuilder.newBuilder().username("Apollo12").isActive(true).password("Tricolore88").confirmPassword("Tricolore88").
                email("Apollo12").build();
        long idDescriptionReport = 1L;
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("afff").build();
        //act
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(reportService.getReportById(anyLong())).thenReturn(reportDto);
        mockMvc.perform(get("/api/v1/reports/"+idDescriptionReport).
                header(HEADER,JWT)).
                andDo(print()).
                andExpect(status().isOk()).
                andDo(print());
        verify(reportService,times(1)).getReportById(anyLong());
        verify(userService,times(1)).findByUsername(anyString());

    }

    @Test
    public void update_ShouldUpdate() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType(ReportType.AFK.toString()).id(1).build();
        //act
        final User  user = UserBuilder.newBuilder().username("Apollo12").isActive(true).password("Tricolore88").confirmPassword("Tricolore88").
                email("Apollo12").build();

        when(userService.findByUsername(anyString())).thenReturn(user);
        when(reportService.getReportById(anyLong())).thenReturn(reportDto);
        doNothing().when(reportService).delete(anyLong());
        when(reportService.createReport(any(ReportDto.class))).thenReturn(reportDto);
        mockMvc.perform(put("/api/v1/reports/").
                header(HEADER,JWT).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(reportDto))).
                andExpect(status().isOk()).
                andDo(print());
        //assert
        verify(userService,times(1)).findByUsername(anyString());
        verify(reportService,times(1)).getReportById(anyLong());
        verify(reportService,times(1)).delete(anyLong());
        verify(reportService,times(1)).createReport(any(ReportDto.class));
    }

    @Test
    public void update_ShouldThrowErrorWhenReportTypeIsBlank() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("").build();
        //act
        mockMvc.perform(put("/api/v1/reports/").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(reportDto)).
                header(HEADER,JWT)).
                andExpect(status().isBadRequest()).
                andDo(print());
        //assert
        verify(userService,times(0)).findByUsername(anyString());
        verify(reportService,times(0)).getReportById(anyLong());
        verify(reportService,times(0)).delete(anyLong());
        verify(reportService,times(0)).createReport(any(ReportDto.class));
    }

    /*@Test
    public void get_ShouldReturnReportsOfPlayerByGivenId() throws Exception {
        //arrange
        int startFrom=0;
        final User  user = UserBuilder.newBuilder().username("Apollo12").isActive(true).password("Tricolore88").confirmPassword("Tricolore88").region(Server.Region.EUW).
                email("Apollo12").build();
        final List<ReportDto> reportDtos = new ArrayList<>();
        reportDtos.add(ReportDtoBuilder.newBuilder().description("Flame").nickname("Apollo0")
                .reportType(ReportType.AFK.toString()).user(new User()).region(Server.Region.EUW).build());
        reportDtos.add(ReportDtoBuilder.newBuilder().description("He was afk").nickname("Apollo1")
                .reportType(ReportType.AFK.toString()).user(new User()).region(Server.Region.EUW).build());
        reportDtos.add(ReportDtoBuilder.newBuilder().description("He was afk").nickname("Apollo2")
                .reportType(ReportType.AFK.toString()).user(new User()).region(Server.Region.EUW).build());
        reportDtos.add(ReportDtoBuilder.newBuilder().description("He was afk").nickname("Apollo3")
                .reportType(ReportType.AFK.toString()).user(new User()).region(Server.Region.EUW).build());
        reportDtos.add(ReportDtoBuilder.newBuilder().description("He was afk").nickname("Apollo4")
                .reportType(ReportType.AFK.toString()).user(new User()).region(Server.Region.EUW).build());
        reportDtos.add(ReportDtoBuilder.newBuilder().description("He was afk").nickname("Apollo5")
                .reportType(ReportType.AFK.toString()).user(new User()).region(Server.Region.EUW).build());
        //ac
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(playerService.getPlayerById(anyLong())).thenReturn(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo10").build());
        when(reportService.getReportsByPlayerId(anyLong())).thenReturn(reportDtos);
        mockMvc.perform(get("/api/v1/reports/players/1?startFrom="+startFrom)
                .header(HEADER,JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickname").value("Apollo0"))
                .andExpect(jsonPath("$[1].nickname").value("Apollo1"))
                .andExpect(jsonPath("$[2].nickname").value("Apollo2"))
                .andExpect(jsonPath("$[3].nickname").value("Apollo3"))
                .andExpect(jsonPath("$[4].nickname").value("Apollo4"))
                .andDo(print());
        //assert
        verify(userService,times(1)).findByUsername(anyString());
        verify(playerService,times(1)).getPlayerById(anyLong());
        verify(reportService,times(1)).getReportsByPlayerId(anyLong());
    }*/

}
