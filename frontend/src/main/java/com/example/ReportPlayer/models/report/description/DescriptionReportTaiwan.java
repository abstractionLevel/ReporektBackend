package com.example.ReportPlayer.models.report.description;

import com.example.ReportPlayer.models.comment.CommentTaiwan;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerTaiwan;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_taiwan")
public class DescriptionReportTaiwan extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_taiwan_id", nullable = false)
    private PlayerTaiwan player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentTaiwan> comment;

    public DescriptionReportTaiwan() {

    }

    public DescriptionReportTaiwan(String description) {
        super(description);
    }


    @Override
    public PlayerTaiwan getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerTaiwan) player;
    }


}
