package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentSingapore;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerSingapore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_singapore")
public class DescriptionReportSingapore extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_singapore_id", nullable = false)
    private PlayerSingapore player;



    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentSingapore> comment;

    public DescriptionReportSingapore() {

    }

    public DescriptionReportSingapore(String description) {
        super(description);
    }

    @Override
    public PlayerSingapore getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerSingapore) player;
    }



}
