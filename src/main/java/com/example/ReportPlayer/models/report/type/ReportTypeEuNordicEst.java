package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuNordicEst;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_eu_nordic_est")
public class ReportTypeEuNordicEst extends ReportType{
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_eu_nordic_est_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerEuNordicEst player;

    public ReportTypeEuNordicEst(String reportType) {
        super(reportType);
    }

    public ReportTypeEuNordicEst() {

    }

    @Override
    public PlayerEuNordicEst getPlayer() {
        return player;
    }


    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerEuNordicEst )player;
    }
}
