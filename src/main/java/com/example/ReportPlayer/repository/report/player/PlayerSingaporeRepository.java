package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerSingapore;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.SINGAPORE)
public interface PlayerSingaporeRepository extends PlayerBaseRepository<PlayerSingapore> {
}
