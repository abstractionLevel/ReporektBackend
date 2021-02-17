package com.example.ReportPlayer.services.report.player;


import com.example.ReportPlayer.models.report.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Player save(Player player);
    Player findByNickname(String nickname);
    Player delete(long id);
    List<Player> getTopPlayers();
    List<Player> getLastReportedPlayers();
    Player getPlayerById(long id);
    Player findByPlayer(Player player);
}
