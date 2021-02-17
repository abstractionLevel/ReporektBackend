package com.example.ReportPlayer.controller.models.entity;

import com.example.ReportPlayer.models.report.description.*;
import com.example.ReportPlayer.models.report.player.*;
import com.example.ReportPlayer.models.report.type.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class ReportDomainTest {




    @Test
    public void reportEuw_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerEuw("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportEuw("it was afk");
        final ReportType reportType = new ReportTypeEuw("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerEuw);
        assertTrue(descriptionReport instanceof DescriptionReportEuw);
        assertTrue(reportType instanceof ReportTypeEuw);
    }

    @Test
    public void reportNa_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerNa("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportNa("it was afk");
        final ReportType reportType = new ReportTypeNa("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerNa);
        assertTrue(descriptionReport instanceof DescriptionReportNa);
        assertTrue(reportType instanceof ReportTypeNa);
    }

    @Test
    public void reportKorea_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerKorea("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportKorea("it was afk");
        final ReportType reportType = new ReportTypeKorea("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerKorea);
        assertTrue(descriptionReport instanceof DescriptionReportKorea);
        assertTrue(reportType instanceof ReportTypeKorea);
    }

    @Test
    public void reportJapan_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerJapan("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportJapan("it was afk");
        final ReportType reportType = new ReportTypeJapan("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerJapan);
        assertTrue(descriptionReport instanceof DescriptionReportJapan);
        assertTrue(reportType instanceof ReportTypeJapan);
    }

    @Test
    public void reportEuwNordicEst_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerEuNordicEst("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportEuNordicEst("it was afk");
        final ReportType reportType = new ReportTypeEuNordicEst("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerEuNordicEst);
        assertTrue(descriptionReport instanceof DescriptionReportEuNordicEst);
        assertTrue(reportType instanceof ReportTypeEuNordicEst);
    }

    @Test
    public void reportOceania_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerOceania("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportOceania("it was afk");
        final ReportType reportType = new ReportTypeOceania("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerOceania);
        assertTrue(descriptionReport instanceof DescriptionReportOceania);
        assertTrue(reportType instanceof ReportTypeOceania);
    }

    @Test
    public void reportBrazil_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerBrazil("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportBrazil("it was afk");
        final ReportType reportType = new ReportTypeBrazil("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerBrazil);
        assertTrue(descriptionReport instanceof DescriptionReportBrazil);
        assertTrue(reportType instanceof ReportTypeBrazil);
    }

    @Test
    public void reportLas_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerLas("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportLas("it was afk");
        final ReportType reportType = new ReportTypeLas("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerLas);
        assertTrue(descriptionReport instanceof DescriptionReportLas);
        assertTrue(reportType instanceof ReportTypeLas);
    }

    @Test
    public void reportLan_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerLan("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportLan("it was afk");
        final ReportType reportType = new ReportTypeLan("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerLan);
        assertTrue(descriptionReport instanceof DescriptionReportLan);
        assertTrue(reportType instanceof ReportTypeLan);
    }

    @Test
    public void reportRussia_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerTurkey("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportTurkey("it was afk");
        final ReportType reportType = new ReportTypeTurkey("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerTurkey);
        assertTrue(descriptionReport instanceof DescriptionReportTurkey);
        assertTrue(reportType instanceof ReportTypeTurkey);
    }

    @Test
    public void reportSingapore_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerSingapore("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportSingapore("it was afk");
        final ReportType reportType = new ReportTypeSingapore("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerSingapore);
        assertTrue(descriptionReport instanceof DescriptionReportSingapore);
        assertTrue(reportType instanceof ReportTypeSingapore);
    }

    @Test
    public void reportIndonesia_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerIndonesia("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportIndonesia("it was afk");
        final ReportType reportType = new ReportTypeIndonesia("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerIndonesia);
        assertTrue(descriptionReport instanceof DescriptionReportIndonesia);
        assertTrue(reportType instanceof ReportTypeIndonesia);
    }

    @Test
    public void reportPhilippines_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerPhilippines("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportPhilippines("it was afk");
        final ReportType reportType = new ReportTypePhilippines("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerPhilippines);
        assertTrue(descriptionReport instanceof DescriptionReportPhilippines);
        assertTrue(reportType instanceof ReportTypePhilippines);
    }

    @Test
    public void reportTaiwan_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerTaiwan("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportTaiwan("it was afk");
        final ReportType reportType = new ReportTypeTaiwan("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerTaiwan);
        assertTrue(descriptionReport instanceof DescriptionReportTaiwan);
        assertTrue(reportType instanceof ReportTypeTaiwan);
    }

    @Test
    public void reportVietnam_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerVietnam("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportVietnam("it was afk");
        final ReportType reportType = new ReportTypeVietnam("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerVietnam);
        assertTrue(descriptionReport instanceof DescriptionReportVietnam);
        assertTrue(reportType instanceof ReportTypeVietnam);
    }

    @Test
    public void reportThailand_testObjectIsOfTheRightInstance() throws Exception {
        //arrange
        final Player player = new PlayerThailand("apollo");
        final DescriptionReport descriptionReport = new DescriptionReportThailand("it was afk");
        final ReportType reportType = new ReportTypeThailand("Afk");
        //act
        //assert
        assertTrue(player instanceof PlayerThailand);
        assertTrue(descriptionReport instanceof DescriptionReportThailand);
        assertTrue(reportType instanceof ReportTypeThailand);
    }

}
