package com.example.ReportPlayer.factory.report;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.description.*;

public class DescriptionReportFactory {


    public static DescriptionReport getDescriptionReport(String region) {
        DescriptionReport descriptionReport = null;
        switch (region) {
            case Server.Region.EUW:
                descriptionReport = new DescriptionReportEuw();
                break;
            case Server.Region.NORTH_AMERICA:
                descriptionReport = new DescriptionReportNa();
                break;
            case Server.Region.KOREA:
                descriptionReport = new DescriptionReportKorea();
                break;
            case Server.Region.BRAZIL:
                descriptionReport = new DescriptionReportBrazil();
                break;
            case Server.Region.JAPAN:
                descriptionReport = new DescriptionReportJapan();
                break;
            case Server.Region.EU_NORDIC_AND_EST:
                descriptionReport = new DescriptionReportEuNordicEst();
                break;
            case Server.Region.OCEANIA:
                descriptionReport = new DescriptionReportOceania();
                break;
            case Server.Region.LAS:
                descriptionReport = new DescriptionReportLas();
                break;
            case Server.Region.LAN:
                descriptionReport = new DescriptionReportLan();
                break;
            case Server.Region.RUSSIA:
                descriptionReport = new DescriptionReportRussia();
                break;
            case Server.Region.SINGAPORE:
                descriptionReport = new DescriptionReportSingapore();
                break;
            case Server.Region.INDONESIA:
                descriptionReport = new DescriptionReportIndonesia();
                break;
            case Server.Region.PHILIPPINES:
                descriptionReport = new DescriptionReportPhilippines();
                break;
            case Server.Region.THAILAND:
                descriptionReport = new DescriptionReportThailand();
                break;
            case Server.Region.VIETNAM:
                descriptionReport = new DescriptionReportVietnam();
                break;
            case Server.Region.TAIWAN:
                descriptionReport = new DescriptionReportTaiwan();
                break;
            case Server.Region.TURKEY:
                descriptionReport = new DescriptionReportTurkey();
                break;
            default:
                descriptionReport = null;
                break;
        }

        return descriptionReport;
    }
}
