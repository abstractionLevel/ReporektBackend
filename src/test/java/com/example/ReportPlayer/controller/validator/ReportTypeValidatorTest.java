package com.example.ReportPlayer.controller.validator;

import com.example.ReportPlayer.enumerated.ReportType;
import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.builder.ReportDtoBuilder;
import com.example.ReportPlayer.dto.report.ReportDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ReportTypeValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    public void reportType_ShouldValidate() throws Exception {
        //arrange
        final ReportDto reportDto =  ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType(ReportType.AFK.toString()).build();
        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        assertEquals(0 , violations.size());
    }

    @Test
    public void reportType_ShouldInvalidate() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("afkkk").build();
        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        assertEquals(1 , violations.size());
    }


}
