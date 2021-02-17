package com.example.ReportPlayer.controller.builder.player;

import com.example.ReportPlayer.builder.report.player.TopPlayerDtoBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import com.example.ReportPlayer.dto.report.TopPlayerDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuw;
import com.example.ReportPlayer.models.report.type.ReportType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TopPlayerDtoBuilderTest {

    @Test
    public void shouldCheckIfFieldMatches() throws Exception {
        //arrange
        List<String> reportTypes = new ArrayList<>();
        reportTypes.add("Afk");
        reportTypes.add("Troll");
        final TopPlayerDto topPlayerDto = TopPlayerDtoBuilder.newBuilder().nickname("Apollo").reportCount(200).reportType(reportTypes).build();
        //act
        //assert
        assertEquals("Apollo",topPlayerDto.getNickname());
        assertEquals(200,topPlayerDto.getReportCount());
        assertEquals("afk",topPlayerDto.getReportType().get(0));
        assertEquals("Verbal Abusing",topPlayerDto.getReportType().get(1));

    }
}
