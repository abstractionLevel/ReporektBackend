package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerOceania;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_oceania")
public class ReportTypeOceania extends  ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_oceania_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerOceania player;

    public ReportTypeOceania(String reportType) {
        super(reportType);
    }

    public ReportTypeOceania() {

    }


    @Override
    public PlayerOceania getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerOceania)player;
    }
}
