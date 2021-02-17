package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentLan;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerLan;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_lan")
public class DescriptionReportLan  extends DescriptionReport{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_lan_id", nullable = false)
    private PlayerLan player;



    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentLan> comment;

    public DescriptionReportLan() {

    }
    public DescriptionReportLan(String description) {
        super(description);
    }

    @Override
    public PlayerLan getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerLan)player;
    }

}
