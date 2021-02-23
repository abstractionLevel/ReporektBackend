package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerNa;
import org.springframework.stereotype.Repository;


@Repository("player_repository_"+ Server.Region.NORTH_AMERICA)
public interface PlayerNaRepository extends PlayerBaseRepository<PlayerNa> {
}
