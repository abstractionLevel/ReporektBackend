package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.DescriptionReportFactory;
import com.example.ReportPlayer.models.report.description.DescriptionReport;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class DescriptionReportFactoryTest {

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void descriptionReport_ShouldGetObj(String region) throws Exception {
        //arrange
        //act
        final DescriptionReport descriptionReportExpected = DescriptionReportFactory.getDescriptionReport(region);
        //assert
        System.out.println(descriptionReportExpected);
        assertTrue(descriptionReportExpected instanceof  DescriptionReport);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
