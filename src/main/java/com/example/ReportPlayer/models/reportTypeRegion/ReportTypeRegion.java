package com.example.ReportPlayer.models.reportTypeRegion;

import javax.persistence.*;

@Entity
@Table(name = "report_type_region")
public class ReportTypeRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "report_type")
    private String reportType;
    @Column(name = "count")
    private int count;
    @Column(name = "region")
    private String region;

    public ReportTypeRegion() {}
    public ReportTypeRegion(String reportType, int count , String region ){
        this.reportType = reportType;
        this.count = count;
        this.region = region;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
