package com.example.ReportPlayer.models.helpCenter;

import javax.persistence.*;

@Entity
public class ContactUs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    private String user;

    public ContactUs() {

    }

    public ContactUs(String type,String title,String description,String user) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
