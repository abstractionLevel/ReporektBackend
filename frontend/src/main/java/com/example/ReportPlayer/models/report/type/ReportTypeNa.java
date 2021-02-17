package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerNa;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_na")
public class ReportTypeNa extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_na_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerNa player;

    public ReportTypeNa(String reportType) {
        super(reportType);
    }

    public ReportTypeNa() {

    }

    @Override
    public PlayerNa getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerNa)player;
    }
}
