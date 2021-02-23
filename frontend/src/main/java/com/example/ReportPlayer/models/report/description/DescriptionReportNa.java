package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentNa;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerNa;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_na")
public class DescriptionReportNa extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_na_id", nullable = false)
    private PlayerNa player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentNa> comment;


    public DescriptionReportNa() {

    }
    public DescriptionReportNa(String description) {
        super(description);
    }

    @Override
    public PlayerNa getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerNa)player;
    }



}
