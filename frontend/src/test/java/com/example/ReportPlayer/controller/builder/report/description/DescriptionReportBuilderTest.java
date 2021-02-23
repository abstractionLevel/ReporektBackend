package com.example.ReportPlayer.controller.builder.report.description;


import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.builder.report.description.DescriptionReportBuilder;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.builder.user.UserBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class DescriptionReportBuilderTest {


    @ParameterizedTest
    @MethodSource("streamOfRegion")
    public void descriptionReport_ShouldBuildingObject(String region) throws Exception {
        //arrange
        final User user = UserBuilder.newBuilder().username("apollo").
                password("Tricolore99").confirmPassword("Tricolore99").email("apollo@gmail.com").isActive(false).build();
        final Player player = PlayerBuilder.newBuilder().region(region).username("Apollo").build();
        final DescriptionReport descriptionReportExpected = DescriptionReportBuilder.newBuilder().description("it was afk").player(player).
                region(region).reportType(ReportType.AFK.toString()).user(user).id(1L).build();
        //act
        //assert
        assertEquals("it was afk",descriptionReportExpected.getDescription());
        assertNotNull(descriptionReportExpected.getPlayer());
        assertEquals(ReportType.AFK.toString(),descriptionReportExpected.getReportType());
        assertEquals("Apollo",descriptionReportExpected.getPlayer().getNickname());
        assertEquals(user,descriptionReportExpected.getUser());
        assertEquals(1L,descriptionReportExpected.getId());

    }

    //not-test
    private static Stream streamOfRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
