package com.example.ReportPlayer.controller.builder.report.reportType;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.type.ReportType;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.report.type.ReportTypeBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class ReportTypeBuilderTest {


    @ParameterizedTest
    @MethodSource("streamOfRegion")
    public void reportType_ShouldBuildingObject(String region) throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo").region(region).build();
        final ReportType reportTypeExpected= ReportTypeBuilder.newBuilder().reportType(com.example.ReportPlayer.
                enumerated.ReportType.AFK.toString()).region(region).build();
        //act
        //assert
        assertNotNull(reportTypeExpected);
        assertEquals(com.example.ReportPlayer.enumerated.ReportType.AFK.toString(),reportTypeExpected.getReportType().toString());
        assertNotNull(reportTypeExpected.getPlayer());

    }

    //not-test
    private static Stream streamOfRegion() {
        return Stream.of(Server.values());
    }
}
