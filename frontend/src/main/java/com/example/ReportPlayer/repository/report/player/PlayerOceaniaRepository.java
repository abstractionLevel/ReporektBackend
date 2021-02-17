package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerOceania;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.OCEANIA)
public interface PlayerOceaniaRepository extends PlayerBaseRepository<PlayerOceania>{
}
