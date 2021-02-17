package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuw;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_euw")
public class ReportTypeEuw extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_euw_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerEuw player;

    public ReportTypeEuw(String reportType) {
        super(reportType);
    }

    public ReportTypeEuw() {

    }

    @Override
    public PlayerEuw getPlayer() {
        return player;
    }


    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerEuw)player;
    }
}
