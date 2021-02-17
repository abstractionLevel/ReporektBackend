package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerJapan;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_japan")
public class ReportTypeJapan extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_japan_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerJapan player;

    public ReportTypeJapan(String reportType) {
        super(reportType);
    }

    public ReportTypeJapan() {

    }

    @Override
    public PlayerJapan getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerJapan)player;
    }
}
