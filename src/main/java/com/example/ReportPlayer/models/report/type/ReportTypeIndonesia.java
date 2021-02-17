package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerIndonesia;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_indonesia")
public class ReportTypeIndonesia extends ReportType {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_indonesia_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerIndonesia player;

    public ReportTypeIndonesia(String reportType) {
        super(reportType);
    }

    public ReportTypeIndonesia() {

    }


    @Override
    public PlayerIndonesia getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerIndonesia)player;
    }
}
