package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerTurkey;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.TURKEY)
public interface PlayerTurkeyRepository extends PlayerBaseRepository<PlayerTurkey> {
}
