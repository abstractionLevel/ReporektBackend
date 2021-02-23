package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerRussia;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.RUSSIA)
public interface PlayerRussiaRepository extends PlayerBaseRepository<PlayerRussia> {
}
