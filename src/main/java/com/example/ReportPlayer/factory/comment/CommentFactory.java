package com.example.ReportPlayer.factory.comment;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.comment.*;
import com.example.ReportPlayer.models.report.description.*;

public class CommentFactory {


    public static Comment getComment(String region) {
        Comment comment = null;
        switch (region) {
            case Server.Region.EUW:
                comment = new CommentEuw();
                break;
            case Server.Region.NORTH_AMERICA:
                comment = new CommentNa();
                break;
            case Server.Region.KOREA:
                comment = new CommentKorea();
                break;
            case Server.Region.BRAZIL:
                comment = new CommentBrazil();
                break;
            case Server.Region.JAPAN:
                comment = new CommentJapan();
                break;
            case Server.Region.EU_NORDIC_AND_EST:
                comment = new CommentEuNe();
                break;
            case Server.Region.OCEANIA:
                comment = new CommentOceania();
                break;
            case Server.Region.LAS:
                comment = new CommentLas();
                break;
            case Server.Region.LAN:
                comment = new CommentLan();
                break;
            case Server.Region.RUSSIA:
                comment = new CommentRussia();
                break;
            case Server.Region.SINGAPORE:
                comment = new CommentSingapore();
                break;
            case Server.Region.INDONESIA:
                comment = new CommentIndonesia();
                break;
            case Server.Region.PHILIPPINES:
                comment = new CommentPhilippines();
                break;
            case Server.Region.THAILAND:
                comment = new CommentThailand();
                break;
            case Server.Region.VIETNAM:
                comment = new CommentVietnam();
                break;
            case Server.Region.TAIWAN:
                comment = new CommentTaiwan();
                break;
            case Server.Region.TURKEY:
                comment = new CommentTurkey();
                break;
            default:
                comment = null;
                break;
        }
        return comment;
    }
}
