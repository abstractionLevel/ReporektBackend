package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerRussia;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_russia")
public class ReportTypeRussia extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_russia_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerRussia player;

    public ReportTypeRussia(String reportType) {
        super(reportType);
    }

    public ReportTypeRussia() {

    }

    @Override
    public PlayerRussia getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerRussia)player;
    }
}
