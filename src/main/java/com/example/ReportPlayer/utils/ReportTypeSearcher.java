package com.example.ReportPlayer.utils;

import com.example.ReportPlayer.enumerated.ReportType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportTypeSearcher {

    public static boolean reportTypeExists(String reportType) {
        List<String> enumNames = Stream.of(ReportType.values()).map(ReportType::toString).collect(Collectors.toList());
        for(String r: enumNames) {
            if(reportType.equals(r))
                return true;
        }
        return false;
    }

}
