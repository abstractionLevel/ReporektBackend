package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentKorea;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerKorea;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_korea")
public class DescriptionReportKorea extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_korea_id", nullable = false)
    private PlayerKorea player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentKorea> comment;


    public DescriptionReportKorea() {

    }

    public DescriptionReportKorea(String description) {
        super(description);
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
