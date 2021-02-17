package com.example.ReportPlayer.models.report.description;

import com.example.ReportPlayer.models.comment.CommentRussia;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerRussia;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_russia")
public class DescriptionReportRussia  extends DescriptionReport{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_russia_id", nullable = false)
    private PlayerRussia player;



    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentRussia> comment;

    public DescriptionReportRussia() {

    }

    public DescriptionReportRussia(String description) {
        super(description);
    }

    @Override
    public PlayerRussia getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerRussia)player;
    }


}
