package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerThailand;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_thailand")
public class ReportTypeThailand extends ReportType {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_thailand_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerThailand player;

    public ReportTypeThailand(String reportType) {
        super(reportType);
    }

    public ReportTypeThailand() {

    }

    @Override
    public PlayerThailand getPlayer() {
        return player;
    }


    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerThailand)player;
    }
}
