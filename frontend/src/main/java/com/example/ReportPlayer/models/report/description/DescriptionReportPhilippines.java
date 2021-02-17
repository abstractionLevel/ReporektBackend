package com.example.ReportPlayer.models.report.description;

;
import com.example.ReportPlayer.models.comment.CommentPhilippines;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerPhilippines;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_philippines")
public class DescriptionReportPhilippines extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_philippines_id", nullable = false)
    private PlayerPhilippines player;


    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentPhilippines> comment;

    public DescriptionReportPhilippines() {

    }


    public DescriptionReportPhilippines(String description) {
        super(description);
    }

    @Override
    public PlayerPhilippines getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerPhilippines) player;
    }


}
