package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerEuNordicEst;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.EU_NORDIC_AND_EST)
public interface PlayerEuNeRepository extends PlayerBaseRepository<PlayerEuNordicEst> {
}
