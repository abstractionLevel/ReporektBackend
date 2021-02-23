package com.example.ReportPlayer.repository.report.player;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.PlayerVietnam;
import org.springframework.stereotype.Repository;

@Repository("player_repository_"+ Server.Region.VIETNAM)
public interface PlayerVietnamRepository extends PlayerBaseRepository<PlayerVietnam> {
}
