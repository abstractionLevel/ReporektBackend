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
public class ReportDtoTest {


    @Autowired
    private Validator validator;

    private String region = "euw";



    @Test
    public void reportDto_ShouldInvalidate() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().nickname("").reportType("").description("").region(Server.Region.EUW).build();
        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        System.out.println(violations.size());
        assertEquals(4,violations.size());
    }
    @Test
    public void reportDto_ShouldValidate() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType(ReportType.AFK.toString()).build();

        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        System.out.println(violations.size());
        assertEquals(0,violations.size());
    }

    @Test
    public void reportDto_ShouldInvalidatePlayerDto() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("").description("it was afk").reportType(ReportType.AFK.toString()).build();

        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        System.out.println(violations.size());
        assertEquals(1,violations.size());
    }

    @Test
    public void reportDto_ShouldInvalidateDescriptionReportDto() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("").reportType(ReportType.AFK.toString()).build();
        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        System.out.println(violations.size());
        System.out.println(violations.iterator().next().getPropertyPath());
        assertEquals(1,violations.size());
    }

    @Test
    public void reportDto_ShouldInvalidateWrongReportTypeDto() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("fda").build();
        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        System.out.println(violations.size());
        System.out.println(violations.iterator().next().getMessage());
        assertEquals(1,violations.size());
    }

    @Test
    public void reportDto_ShouldInvalidateBlankReportTypeDto() throws Exception {
        //arrange
        final ReportDto reportDto = ReportDtoBuilder.newBuilder().
                region(Server.Region.EUW).nickname("apollo11").description("it was afk").reportType("").build();
        //act
        Set<ConstraintViolation<ReportDto>> violations = validator.validate(reportDto);
        //assert
        System.out.println(violations.size());
        System.out.println(violations.iterator().next().getMessage());
        assertEquals(2,violations.size());
    }
}
