package com.example.ReportPlayer.controller.dto;

import com.example.ReportPlayer.builder.comment.CommentDtoBuilder;
import com.example.ReportPlayer.dto.comment.CommentDto;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import com.example.ReportPlayer.models.report.description.DescriptionReportNa;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class CommentDtoTest {


    @Test
    public void shouldCheckFields() throws Exception {
        //arrange
        final CommentDto  commentDto = CommentDtoBuilder.newBuilder().comment("he was afk").user(new User()).report(new DescriptionReport()).region(Server.Region.EUW).build();
        //act
        //assert
        assertEquals("he was afk",commentDto.getComment());
        assertEquals(Server.Region.EUW,commentDto.getRegion());
        assertNotNull(commentDto.getDescriptionReport());
        assertNotNull(commentDto.getUser());
    }

}
