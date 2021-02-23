package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerVietnam;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_vietnam")
public class ReportTypeVietnam extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_vietnam_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerVietnam player;

    public ReportTypeVietnam(String reportType) {
        super(reportType);
    }

    public ReportTypeVietnam() {

    }

    @Override
    public PlayerVietnam getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerVietnam) player;
    }
}
