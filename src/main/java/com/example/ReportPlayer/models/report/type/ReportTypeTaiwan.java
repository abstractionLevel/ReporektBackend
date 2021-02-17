package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerTaiwan;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_taiwan")
public class ReportTypeTaiwan extends ReportType {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_taiwan_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerTaiwan player;

    public ReportTypeTaiwan(String reportType) {
        super(reportType);
    }

    public ReportTypeTaiwan() {

    }

    @Override
    public PlayerTaiwan getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerTaiwan)player;
    }
}
