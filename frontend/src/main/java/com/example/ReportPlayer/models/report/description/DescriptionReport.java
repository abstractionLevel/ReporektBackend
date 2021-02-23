package com.example.ReportPlayer.models.report.description;

import com.example.ReportPlayer.models.user.User;
import com.example.ReportPlayer.models.report.player.Player;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class DescriptionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "description",length = 13000)
    private String description;
    @Column(name = "report_type")
    private String reportType;
    @Column(name = "date")
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Transient
    private Player player;






    public DescriptionReport() {}

    public DescriptionReport(String description) {
        this.description = description;
    }

    public DescriptionReport(String description,Player player) {
        this.description = description;
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }


    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
