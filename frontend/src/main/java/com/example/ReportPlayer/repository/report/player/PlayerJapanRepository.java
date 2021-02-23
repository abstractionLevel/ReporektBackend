package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerJapan;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.JAPAN)
public interface PlayerJapanRepository extends PlayerBaseRepository<PlayerJapan>{
}
