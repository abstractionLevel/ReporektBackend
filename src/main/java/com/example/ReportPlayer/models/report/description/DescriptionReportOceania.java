package com.example.ReportPlayer.models.report.description;

import com.example.ReportPlayer.models.comment.CommentOceania;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerOceania;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_oceania")
public class DescriptionReportOceania extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_oceania_id", nullable = false)
    private PlayerOceania player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentOceania> comment;

    public DescriptionReportOceania() {

    }

    public DescriptionReportOceania(String description) {
        super(description);
    }

    @Override
    public PlayerOceania getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerOceania)player;
    }


}
