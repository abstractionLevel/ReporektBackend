package com.example.ReportPlayer.factory.report;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.report.player.*;

public  class PlayerFactory {

    public static Player getPlayer(String region) {
        Player player = null;
        switch (region) {
            case Server.Region.EUW:
                player = new PlayerEuw();
                break;
            case Server.Region.NORTH_AMERICA:
                player = new PlayerNa();
                break;
            case Server.Region.KOREA:
                player = new PlayerKorea();
                break;
            case Server.Region.BRAZIL:
                player = new PlayerBrazil();
                break;
            case Server.Region.JAPAN:
                player = new PlayerJapan();
                break;
            case Server.Region.EU_NORDIC_AND_EST:
                player = new PlayerEuNordicEst();
                break;
            case Server.Region.OCEANIA:
                player = new PlayerOceania();
                break;
            case Server.Region.LAS:
                player = new PlayerLas();
                break;
            case Server.Region.LAN:
                player = new PlayerLan();
                break;
            case Server.Region.RUSSIA:
                player = new PlayerRussia();
                break;
            case Server.Region.SINGAPORE:
                player = new PlayerSingapore();
                break;
            case Server.Region.INDONESIA:
                player = new PlayerIndonesia();
                break;
            case Server.Region.PHILIPPINES:
                player = new PlayerPhilippines();
                break;
            case Server.Region.THAILAND:
                player = new PlayerThailand();
                break;
            case Server.Region.VIETNAM:
                player = new PlayerVietnam();
                break;
            case Server.Region.TAIWAN:
                player = new PlayerTaiwan();
                break;
            case Server.Region.TURKEY:
                player = new PlayerTurkey();
                break;
            default:
                player = null;
                break;
        }
        return player;

    }
}
