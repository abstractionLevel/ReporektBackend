package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerTaiwan;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.TAIWAN)
public interface PlayerTaiwanRepository extends PlayerBaseRepository<PlayerTaiwan> {
}
