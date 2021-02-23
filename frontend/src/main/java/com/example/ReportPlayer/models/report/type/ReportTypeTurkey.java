package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerTurkey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_turkey")
public class ReportTypeTurkey extends ReportType {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_turkey_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerTurkey player;

    public ReportTypeTurkey(String reportType) {
        super(reportType);
    }

    public ReportTypeTurkey() {

    }

    @Override
    public PlayerTurkey getPlayer() {
        return player;
    }


    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerTurkey)player;
    }
}
