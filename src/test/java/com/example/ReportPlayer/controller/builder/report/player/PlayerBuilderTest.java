package com.example.ReportPlayer.controller.builder.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import com.example.ReportPlayer.models.report.player.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PlayerBuilderTest {

    @Test
    public void playerEuw_ShouldInstantiate() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo").build();
        //act
        //assert
        assertTrue(player instanceof PlayerEuw);
    }

    @Test
    public void playerNa_ShouldInstantiate() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.NORTH_AMERICA).username("Apollo").build();
        //act
        //assert
        assertTrue(player instanceof PlayerNa);
    }

    @Test
    public void playerBrazil_ShouldInstantiate() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.BRAZIL).username("Apollo").build();
        //act
        //assert
        assertTrue(player instanceof PlayerBrazil);
    }

    @Test
    public void playerKorea_ShouldInstantiate() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.KOREA).username("Apollo").build();
        //act
        //assert
        assertTrue(player instanceof PlayerKorea);
    }

    @Test
    public void shouldCheckIfFieldMatches() throws Exception {
        //arrange
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);
        System.out.println(date); //Tue Aug 31 10:20:56 SGT 1982
        final Player playerExpected = PlayerBuilder.newBuilder().region(Server.Region.KOREA).username("Apollo").date(date).build();
        //act
        //assert
        System.out.println(playerExpected);
    }




}
