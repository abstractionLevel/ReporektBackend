package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentEuNe;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerEuNordicEst;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_eu_nordic_est")
public class DescriptionReportEuNordicEst extends DescriptionReport{
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_eu_nordic_est_id", nullable = false)
    private PlayerEuNordicEst player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentEuNe> comment;

    public DescriptionReportEuNordicEst(String description) {
        super(description);
    }

    public DescriptionReportEuNordicEst() {

    }

    @Override
    public PlayerEuNordicEst getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerEuNordicEst)player;
    }


}
