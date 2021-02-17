package com.example.ReportPlayer.controller.controller.api.report;

import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuw;
import com.example.ReportPlayer.models.report.type.ReportTypeEuw;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.services.report.player.PlayerService;
import com.example.ReportPlayer.services.report.type.ReportTypeService;
import com.example.ReportPlayer.services.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static com.example.ReportPlayer.controller.controller.api.JwtToken.TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Qualifier("player_service_"+ Server.Region.EUW)
    private PlayerService playerService;
    @MockBean
    @Qualifier("report_type_service_"+ Server.Region.EUW)
    private ReportTypeService reportTypeService;
    @MockBean
    private UserServiceImpl userService;

    private String HEADER = "Bearer";
    private String JWT = TOKEN;



    @Test
    public void find_getTopPlayers() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").isActive(true).password("Tricolore88").confirmPassword("Tricolore88").
                email("Apollo12").build();
        final List<Player> players = new ArrayList<>();
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo").reportCount(200).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Michael").reportCount(100).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("George").reportCount(40).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Nicol").reportCount(10).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Edward").reportCount(4).build());

        final List<ReportType> reportTypes = new ArrayList<>();
        reportTypes.add(new ReportTypeEuw());
        //act
        when(playerService.getTopPlayers()).thenReturn(players);
        when(reportTypeService.findAllReportTypeByPlayerId(anyLong())).thenReturn(reportTypes);
        when(userService.findByUsername(anyString())).thenReturn(user);
        mockMvc.perform(get("/api/public/v1/players/top")
                .header(HEADER,JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickname").value(players.get(0).getNickname()))
                .andExpect(jsonPath("$[0].reportCount").value(players.get(0).getReportCount()))
                .andExpect(jsonPath("$[0].reportType[0].reportType").value(reportTypes.get(0)))
                .andDo(print());
        //assert
        verify(playerService,times(1)).getTopPlayers();
        verify(reportTypeService,times(5)).findAllReportTypeByPlayerId(anyLong());
        verify(userService,times(1)).findByUsername(anyString());
    }

    @Test
    public void find_ShouldReturnTheMostRecentPlayerReported() throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("Apollo12").isActive(true).password("Tricolore88").confirmPassword("Tricolore88").
                email("Apollo12").build();
        final List<Player> players = new ArrayList<>();
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo").reportCount(100).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Michael").reportCount(100).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("George").reportCount(40).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Nicol").reportCount(10).build());
        players.add(PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Edward").reportCount(4).build());
        //act
        when(playerService.getLastReportedPlayers()).thenReturn(players);
        when(userService.findByUsername(anyString())).thenReturn(user);
        mockMvc.perform(get("/api/public/v1/players/latest")
                .header(HEADER,JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickname").value("Apollo"))
                .andExpect(jsonPath("$[1].nickname").value("Michael"))
                .andExpect(jsonPath("$[2].nickname").value("George"))
                .andExpect(jsonPath("$[3].nickname").value("Nicol"))
                .andExpect(jsonPath("$[4].nickname").value("Edward"))
                .andDo(print());
        //assert
        verify(playerService,times(1)).getLastReportedPlayers();
        verify(userService,times(1)).findByUsername(anyString());
    }
}
