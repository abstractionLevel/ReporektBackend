package com.example.ReportPlayer.controller.utility;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.utils.ReportTypeSearcher;
import com.example.ReportPlayer.builder.ReportDtoBuilder;
import com.example.ReportPlayer.dto.report.ReportDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ReportTypeSearcherTest {




    @Test
    public void search_CheckIfAfReportTypeIsValid() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("").build();
        //act
        boolean exist = ReportTypeSearcher.reportTypeExists(reportDto.getReportType());
        //assert
        assertEquals(true,exist);
    }

    @Test
    public void search_CheckIfAllReportTypeAreValid() throws Exception {
        //arrange
        //act
        for(String s: this.listOfReportType()) {
            boolean exist = ReportTypeSearcher.reportTypeExists(s);
            System.out.println(s);
            //assert
            assertEquals(true,exist);
        }
    }

    @Test
    public void search_ReturnFalseIfReportTypeExist() throws  Exception {
        //arrange
        //act
        for(String s: this.listOfInvalidReportType()) {
            System.out.println(s);
            boolean exist = ReportTypeSearcher.reportTypeExists(s);
            //assert
            assertEquals(false,exist);
        }
    }

    private List<String> listOfReportType() {
        return Arrays.asList(new String[] {"Afk","Negative Attitude","Verbal Abuse","Afk","Cheating","Cheating","Hate Speech","Intentional Feeding"});
    }

    private List<String> listOfInvalidReportType() {
        return Arrays.asList(new String[] {"Afkdd","Negative "," Abuse","Ak","Cheing","Cheing","Hate Sech"," Feedig"});
    }

}
