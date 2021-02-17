package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerEuw;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.EUW)
public interface PlayerEuwRepository extends PlayerBaseRepository<PlayerEuw> {
}
