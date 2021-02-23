package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerKorea;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.KOREA)
public interface PlayerKoreaRepository extends PlayerBaseRepository<PlayerKorea> {
}
