package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerIndonesia;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.INDONESIA)
public interface PlayerIndonesiaRepository extends PlayerBaseRepository<PlayerIndonesia> {
}
