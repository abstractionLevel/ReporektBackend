package com.example.ReportPlayer.models.report.type;

import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerBrazil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "report_type_brazil")
public class ReportTypeBrazil extends ReportType{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_brazil_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlayerBrazil player;

    public ReportTypeBrazil(String reportType) {
        super(reportType);
    }

    public ReportTypeBrazil() {

    }

    @Override
    public PlayerBrazil getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerBrazil) player;
    }





}
