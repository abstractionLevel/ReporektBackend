package com.example.ReportPlayer.factory.report;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.type.*;

public class ReportTypeFactory {


    public static ReportType getReportType(String region) {
        ReportType reportType = null;
        switch (region) {
            case Server.Region.EUW:
                reportType = new ReportTypeEuw();
                break;
            case Server.Region.NORTH_AMERICA:
                reportType = new ReportTypeNa();
                break;
            case Server.Region.KOREA:
                reportType = new ReportTypeKorea();
                break;
            case Server.Region.BRAZIL:
                reportType = new ReportTypeBrazil();
                break;
            case Server.Region.JAPAN:
                reportType = new ReportTypeJapan();
                break;
            case Server.Region.EU_NORDIC_AND_EST:
                reportType = new ReportTypeEuNordicEst();
                break;
            case Server.Region.OCEANIA:
                reportType = new ReportTypeOceania();
                break;
            case Server.Region.LAS:
                reportType = new ReportTypeLas();
                break;
            case Server.Region.LAN:
                reportType = new ReportTypeLan();
                break;
            case Server.Region.RUSSIA:
                reportType = new ReportTypeRussia();
                break;
            case Server.Region.SINGAPORE:
                reportType = new ReportTypeSingapore();
                break;
            case Server.Region.INDONESIA:
                reportType = new ReportTypeIndonesia();
                break;
            case Server.Region.PHILIPPINES:
                reportType = new ReportTypePhilippines();
                break;
            case Server.Region.THAILAND:
                reportType = new ReportTypeThailand();
                break;
            case Server.Region.VIETNAM:
                reportType = new ReportTypeVietnam();
                break;
            case Server.Region.TAIWAN:
                reportType = new ReportTypeTaiwan();
                break;
            case Server.Region.TURKEY:
                reportType = new ReportTypeTurkey();
                break;
            default:
                reportType = null;
                break;
        }
        return reportType;

    }
}
