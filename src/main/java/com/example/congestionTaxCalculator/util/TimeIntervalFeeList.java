package com.example.congestionTaxCalculator.util;

import lombok.Data;

import java.util.List;

@Data
public class TimeIntervalFeeList {
    private List<TimeFeeObj> timeFeeList;
    @Data
    public static class TimeFeeObj{
        private String start;
        private String end;
        private Double fee;
    }

}
