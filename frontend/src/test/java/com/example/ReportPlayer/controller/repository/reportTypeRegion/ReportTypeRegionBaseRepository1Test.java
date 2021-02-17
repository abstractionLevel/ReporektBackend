package com.example.ReportPlayer.controller.repository.reportTypeRegion;


import com.example.ReportPlayer.models.reportTypeRegion.ReportTypeRegion;
import com.example.ReportPlayer.repository.reportTypeOfRegion.ReportTypeRegionBaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ReportTypeRegionBaseRepository1Test {


    @Autowired
    private ReportTypeRegionBaseRepository repository;

    @Test
    public void save_ShouldSave() {
        //arrange
        ReportTypeRegion reportTypeRegion = new ReportTypeRegion("Afk",10,"euw");
        //act
        final ReportTypeRegion reportTypeRegionExpexted = repository.save(reportTypeRegion);
        //assert
        assertNotNull(reportTypeRegionExpexted);
    }
}
