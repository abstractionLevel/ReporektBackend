package com.example.ReportPlayer.controller.services.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.exception.PlayerException;
import com.example.ReportPlayer.factory.report.PlayerRepositoryFactory;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;
import com.example.ReportPlayer.services.report.player.PlayerServiceImpl;
import com.example.ReportPlayer.builder.report.player.PlayerBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceImplTest {

    @InjectMocks
    @Qualifier("player_service_"+ Server.Region.EUW)
    private PlayerServiceImpl service;
    @Mock
    private PlayerBaseRepository repository;
    @Mock
    private ApplicationContext context;


    @Test
    public void save_ShouldSavePlayer() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo").build();
        //act
        when(repository.save(player)).thenReturn(player);
        final Player playerExpected = service.save(player);
        //assert
        verify(repository,times(1)).save(player);
        assertEquals("Apollo",playerExpected.getNickname());
        System.out.println(playerExpected.getDate());
    }


    @Test
    public void save_ShouldNotSaveIfPlayerExists() throws Exception {
        //arrange
        final Player player  = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo").build();
        //act
        when(repository.findByNickname(anyString())).thenReturn( player);
        when(PlayerRepositoryFactory.getRepository(Server.Region.EUW,context)).thenReturn(repository);
        final Player playerExpected = service.save(player);
        //assert

        verify(repository,times(0)).save(player);
        verify(repository,times(1)).findByNickname(anyString());
        assertEquals("Apollo",player.getNickname());
        System.out.println(playerExpected.getClass().getSimpleName());
        System.out.println(playerExpected.getDate());
    }

    @Test
    public void findPlayer_ShouldFindByUsername() throws Exception {
        //arrange
        final Player player  = PlayerBuilder.newBuilder().region(Server.Region.EUW).username("Apollo").build();
        //act
        when(repository.findByNickname(player.getNickname())).thenReturn( player);
        when(PlayerRepositoryFactory.getRepository(Server.Region.EUW,context)).thenReturn(repository);
        final Player playerExpected = service.findByNickname(player.getNickname());
        //assert
        assertEquals("Apollo",playerExpected.getNickname());

    }


    @Test
    public void delete_ShouldDeleteIfCountIsEqualToOne() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").id(1L).region(Server.Region.EUW).reportCount(1).build();
        //act
        when(repository.getOne(anyLong())).thenReturn(player);
        final Player playerExpected = service.delete(anyLong());
        //assert
        verify(repository,times(1)).deleteById(anyLong());
        assertNull(playerExpected);
    }

    @Test
    public void delete_ShouldDecrementByOneIfCountIsOverToOne() throws Exception {
        //arrange
        final Player player = PlayerBuilder.newBuilder().username("Apollo1").id(1L).region(Server.Region.EUW).reportCount(2).build();
        //act
        when(repository.getOne(anyLong())).thenReturn(player);
        final Player playerExpected = service.delete(anyLong());
        //assert
        verify(repository,times(0)).deleteById(anyLong());
        assertEquals(1,playerExpected.getReportCount());
        assertNotNull(playerExpected);
    }

    @Test
    public void delete_ShouldThrowsPlayerExceptionIfNotExist() throws Exception {
        //arrange
        //act
        when(repository.getOne(anyLong())).thenReturn(null);
        assertThrows(PlayerException.class, () -> {
            final Player playerExpected = service.delete(anyLong());
            //assert
            verify(repository,times(0)).deleteById(anyLong());
            assertNull(playerExpected);
        });
    }

    @Test
    public void find_ShouldGetTopPlayer() throws Exception {
        //arrange
        List<Player> playerList = new  ArrayList<>();
        playerList.add(PlayerBuilder.newBuilder().reportCount(200).username("Michael").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("Michael").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(1500).username("Apollo").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(10).username("Michael").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("Michael").region(Server.Region.EUW).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(400).username("Michael").region(Server.Region.EUW).build());

        //act
        when(repository.findTop5ByOrderByReportCountDesc()).thenReturn(playerList);
        final List<Player> players  = service.getTopPlayers();
        //assert
        for(Player player: players) {
            System.out.println(player.getReportCount());
        }
        assertNotNull(players);
    }


    @Test
    public void find_ShouldGetLastReportedPlayers() throws Exception {
        //arrange
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date = sdf.parse("31-08-1952 10:20:56");

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date1 = sdf1.parse("31-08-1942 10:20:56");

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date2 = sdf2.parse("31-08-1932 10:20:56");

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date3 = sdf3.parse("31-08-1922 10:20:56");

        SimpleDateFormat sdf4 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date4 = sdf4.parse("31-08-1912 10:20:56");

        SimpleDateFormat sdf5 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date5 = sdf5.parse("31-08-1902 10:20:56");


        List<Player> playerList = new  ArrayList<>();
        playerList.add(PlayerBuilder.newBuilder().reportCount(1500).username("Apollo").region(Server.Region.EUW).date(date1).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(200).username("Michael").region(Server.Region.EUW).date(date).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("George").region(Server.Region.EUW).date(date3).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(10).username("Hercules").region(Server.Region.EUW).date(date4).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(100).username("Marvel").region(Server.Region.EUW).date(date2).build());
        playerList.add(PlayerBuilder.newBuilder().reportCount(400).username("Pippo").region(Server.Region.EUW).date(date5).build());
        //act
        when(repository.findTop3ByOrderByDateDesc()).thenReturn(playerList);
        final List<Player> playersExpected = service.getLastReportedPlayers();
        //assert
        for(Player player: playersExpected) {
            System.out.println(player.getDate());
        }
        assertNotNull(playersExpected);
    }
}
