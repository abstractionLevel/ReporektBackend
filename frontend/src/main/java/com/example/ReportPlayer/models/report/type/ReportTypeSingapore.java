package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerSingapore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_singapore")
public class ReportTypeSingapore extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_singapore_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerSingapore player;

    public ReportTypeSingapore(String reportType) {
        super(reportType);
    }

    public ReportTypeSingapore() {

    }

    @Override
    public PlayerSingapore getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerSingapore)player;
    }
}
