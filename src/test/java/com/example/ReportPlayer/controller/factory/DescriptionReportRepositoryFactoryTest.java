package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.DescriptionReportRepositoryFactory;
import com.example.ReportPlayer.repository.report.description.DescriptionReportBaseRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class DescriptionReportRepositoryFactoryTest {

    @Autowired
    private ApplicationContext context;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldReturnRepositoryBeanForEachRegion(String region) throws Exception {
        //arrange
        //act
        final DescriptionReportBaseRepository reportBaseRepository = DescriptionReportRepositoryFactory.getRepository(region,context);
        //assert
        assertNotNull(reportBaseRepository);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);
    }
}
