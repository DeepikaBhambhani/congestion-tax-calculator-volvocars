package com.example.congestionTaxCalculator;

import com.example.congestionTaxCalculator.model.Vehicle;
import com.example.congestionTaxCalculator.controller.CongestionTaxCalculatorServiceImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.congestionTaxCalculator.util.Constants.*;

public class CongestionTaxCalculator {


    private CongestionTaxCalculatorServiceImpl tollService;

    public CongestionTaxCalculator(CongestionTaxCalculatorServiceImpl tollService) {
        this.tollService = tollService;
    }
    public int getTollFee(Vehicle vehicle, List<LocalDateTime> dates) {

        if (!tollService.isValid(vehicle, dates)) {
            return 0;
        }

        List<LocalTime> validTimeList = dates.stream()
                .map(LocalDateTime::toLocalTime)
                .filter(time -> tollService.getFee(time) > 0)
                .sorted()
                .collect(Collectors.toList());

        if (validTimeList.isEmpty()) {
            return 0;
        }

        LocalDateTime intervalStart = dates.get(0);
        int totalFee = 0;
        for (LocalDateTime date : dates) {
            double nextFee = tollService.getFee(date.toLocalTime());
            double tempFee = tollService.getFee(intervalStart.toLocalTime());
            long minutes = ChronoUnit.MINUTES.between(intervalStart, date);

            if (minutes <= 60) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            } else {
                totalFee += nextFee;
            }
        }
        if (totalFee > MAX_FEE_FOR_ONE_DAY) totalFee = MAX_FEE_FOR_ONE_DAY;
        return totalFee;
    }

}


