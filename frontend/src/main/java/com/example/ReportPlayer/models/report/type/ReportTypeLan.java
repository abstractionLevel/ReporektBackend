package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerLan;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_lan")
public class ReportTypeLan extends ReportType {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_lan_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerLan player;

    public ReportTypeLan(String reportType) {
        super(reportType);
    }

    public ReportTypeLan() {

    }
    @Override
    public PlayerLan getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerLan) player;
    }
}
