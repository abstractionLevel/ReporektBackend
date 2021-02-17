package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerKorea;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_korea")
public class ReportTypeKorea extends ReportType {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_korea_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerKorea player;

    public ReportTypeKorea(String reportType) {
        super(reportType);
    }

    public ReportTypeKorea() {

    }

    @Override
    public PlayerKorea getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerKorea)player;
    }
}
