package com.example.ReportPlayer.controller.dto;

import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import com.example.ReportPlayer.dto.report.TopPlayerDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TopPlayerDtoTest {

    @Test
    public void shouldCheckFieldAreValid() throws Exception {
        //arrange
        final TopPlayerDto topPlayerDto = new TopPlayerDto();
        final List<String> reportTypes = new ArrayList<>();
        reportTypes.add("Afk");
        reportTypes.add("Troll");
        topPlayerDto.setNickname("Apollo");
        topPlayerDto.setReportCount(100);
        topPlayerDto.setReportType(reportTypes);
        //act
        //assert
        assertEquals("Apollo",topPlayerDto.getNickname());
        assertEquals(200,topPlayerDto.getReportCount());
        assertEquals("afk",topPlayerDto.getReportType().get(0));
        assertEquals(200,topPlayerDto.getReportType().get(0));
        assertEquals("Verbal Abusing",topPlayerDto.getReportType().get(1));
        assertEquals(100,topPlayerDto.getReportType().get(2));
    }
}
