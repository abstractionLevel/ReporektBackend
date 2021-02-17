package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentTurkey;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerTurkey;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_turkey")
public class DescriptionReportTurkey extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_turkey_id", nullable = false)
    private PlayerTurkey player;



    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentTurkey> comment;

    public DescriptionReportTurkey() {

    }
    public DescriptionReportTurkey(String description) {
        super(description);
    }


    @Override
    public PlayerTurkey getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerTurkey) player;
    }


}
