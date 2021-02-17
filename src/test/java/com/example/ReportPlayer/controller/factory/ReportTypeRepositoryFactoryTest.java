package com.example.ReportPlayer.controller.factory;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.factory.report.ReportTypeRepositoryFactory;
import com.example.ReportPlayer.repository.report.type.ReportTypeBaseRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class ReportTypeRepositoryFactoryTest {

    @Autowired
    private ApplicationContext context;

    @ParameterizedTest
    @MethodSource("getAllRegion")
    public void shouldReturnRepositoryBeanForEachRegion(String region) throws Exception {
        //arrange
        //act
        ReportTypeBaseRepository repository = ReportTypeRepositoryFactory.getRepository(region, context);
        //assert
        assertNotNull(repository);
    }

    //non-test
    private static Stream getAllRegion() {
        return Stream.of(Server.values()).map(Server::toString);

    }

}
