package com.example.ReportPlayer.models.report.description;


import com.example.ReportPlayer.models.comment.CommentIndonesia;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerIndonesia;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_indonesia")
public class DescriptionReportIndonesia extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_indonesia_id", nullable = false)
    private PlayerIndonesia player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentIndonesia> comment;

    public DescriptionReportIndonesia() {

    }
    public DescriptionReportIndonesia(String description) {
        super(description);
    }

    @Override
    public PlayerIndonesia getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerIndonesia)player;
    }

}
