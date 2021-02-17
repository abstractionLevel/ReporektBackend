package com.example.ReportPlayer.models.report.description;

import com.example.ReportPlayer.models.comment.CommentBrazil;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerBrazil;


import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "description_report_brazil")
public class DescriptionReportBrazil extends DescriptionReport{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_brazil_id", nullable = false)
    private PlayerBrazil player;



    @OneToMany(mappedBy = "descriptionReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<CommentBrazil> comment = new ArrayList<CommentBrazil>();



    public DescriptionReportBrazil(String description) {
        super(description);
    }


    public DescriptionReportBrazil() {

    }

    @Override
    public PlayerBrazil getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerBrazil)player;
    }

    public Collection<CommentBrazil> getComment() {
        return comment;
    }

    public void setComment(Collection<CommentBrazil> comment) {
        this.comment = comment;
    }
}
