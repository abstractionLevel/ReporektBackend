package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerBrazil;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.BRAZIL)
public interface PlayerBrazilRepository extends PlayerBaseRepository<PlayerBrazil> {
}
