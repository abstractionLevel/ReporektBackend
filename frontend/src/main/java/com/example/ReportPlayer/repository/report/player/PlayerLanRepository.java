package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerLan;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.LAN)
public interface PlayerLanRepository extends PlayerBaseRepository<PlayerLan> {
}