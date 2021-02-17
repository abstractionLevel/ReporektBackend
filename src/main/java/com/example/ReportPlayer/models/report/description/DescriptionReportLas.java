package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentLas;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerLas;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_las")
public class DescriptionReportLas extends DescriptionReport{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_las_id", nullable = false)
    private PlayerLas player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentLas> comment;

    public DescriptionReportLas() {

    }
    public DescriptionReportLas(String description) {
        super(description);
    }

    @Override
    public PlayerLas getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerLas) player;
    }


}
