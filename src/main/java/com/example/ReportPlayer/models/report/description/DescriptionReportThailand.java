package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentThailand;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerThailand;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_thailand")
public class DescriptionReportThailand extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_thailand_id", nullable = false)
    private PlayerThailand player;




    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentThailand> comment;


    public DescriptionReportThailand() {

    }

    public DescriptionReportThailand(String description) {
        super(description);
    }

    @Override
    public PlayerThailand getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player =
                (PlayerThailand) player;
    }

}
