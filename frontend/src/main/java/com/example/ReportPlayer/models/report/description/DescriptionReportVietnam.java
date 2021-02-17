package com.example.ReportPlayer.models.report.description;



import com.example.ReportPlayer.models.comment.CommentVietnam;
import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.models.report.player.PlayerVietnam;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "description_report_vietnam")
public class DescriptionReportVietnam extends DescriptionReport {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_vietnam_id", nullable = false)
    private PlayerVietnam player;



    @OneToMany(mappedBy = "descriptionReport",cascade = CascadeType.ALL)
    private Set<CommentVietnam> comment;

    public DescriptionReportVietnam() {

    }
    public DescriptionReportVietnam(String description) {
        super(description);
    }

    @Override
    public PlayerVietnam getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = (PlayerVietnam)player;
    }


}
