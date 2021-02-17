package com.example.ReportPlayer.validator;

import com.example.ReportPlayer.utils.ReportTypeSearcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReportTypeValidator implements ConstraintValidator<ValidReportType,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ReportTypeSearcher.reportTypeExists(s);
    }

}
