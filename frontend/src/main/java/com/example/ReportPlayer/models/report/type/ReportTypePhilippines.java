package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerPhilippines;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_philippines")
public class ReportTypePhilippines extends ReportType {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_philippines_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerPhilippines player;

    public ReportTypePhilippines(String reportType) {
        super(reportType);
    }

    public ReportTypePhilippines() {

    }

    @Override
    public PlayerPhilippines getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerPhilippines)player;
    }
}
