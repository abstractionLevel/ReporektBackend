package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentEuw;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuw;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_euw")
public class DescriptionReportEuw extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_euw_id", nullable = false)
    private PlayerEuw player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentEuw> comment;


    public DescriptionReportEuw() {

    }
    public DescriptionReportEuw(String description) {
        super(description);
    }

    @Override
    public PlayerEuw getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerEuw) player;
    }





}
