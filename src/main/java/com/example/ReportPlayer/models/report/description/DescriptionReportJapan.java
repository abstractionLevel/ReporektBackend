package com.example.ReportPlayer.models.report.description;

import com.example.ReportPlayer.models.comment.CommentJapan;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerJapan;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_japan")
public class DescriptionReportJapan extends DescriptionReport{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_japan_id", nullable = false)
    private PlayerJapan player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentJapan> comment;

    public DescriptionReportJapan() {

    }
    public DescriptionReportJapan(String description) {
        super(description);
    }

    @Override
    public PlayerJapan getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerJapan )player;
    }

}
