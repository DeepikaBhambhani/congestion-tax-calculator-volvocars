package com.example.congestionTaxCalculator.controller;

import com.example.congestionTaxCalculator.model.Vehicle;
import com.example.congestionTaxCalculator.util.TimeIntervalFee;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.congestionTaxCalculator.util.Constants.*;
import static com.example.congestionTaxCalculator.util.Constants.DIPLOMAT;
import static com.example.congestionTaxCalculator.util.InitialiseData.holidayMap;
import static com.example.congestionTaxCalculator.util.InitialiseData.timeFeeList;
@Service
public class CongestionTaxCalculatorServiceImpl implements CongestionTaxCalculatorService {
    @Override
    public boolean isTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;
        String vehicleType = vehicle.getVehicle();
        return vehicleType.equals(MOTORBIKE) ||
                vehicleType.equals(TRACTOR) ||
                vehicleType.equals(MILITARY) ||
                vehicleType.equals(FOREIGN) ||
                vehicleType.equals(EMERGENCY) ||
                vehicleType.equals(DIPLOMAT);

    }

    @Override
    public boolean isTollFreeDate(LocalDate date)
        {
            int year = date.getYear();

            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return true;
            }else {
                holidayMap.containsKey(date);
            }
            return false;
        }


    @Override
    public boolean isValid(Vehicle vehicle, List<LocalDateTime> dates) {

        Optional.ofNullable(vehicle).orElseThrow(()->new RuntimeException(VEHICLE_NULL_MSG));


        if (isTollFreeVehicle(vehicle)) {
            return false;
        }


        if ( dates==null || CollectionUtils.isEmpty(Arrays.asList(dates))) {
            throw new RuntimeException(DATES_NULL_MSG);
        }

        if(dates.stream().map(LocalDateTime::toLocalDate)
                .anyMatch(date -> !date.equals(dates.get(0).toLocalDate()))){
            throw new RuntimeException(MORE_THAN_ONE_DAY_MSG);
        }

        if (isTollFreeDate(dates.get(0).toLocalDate())) {
            return false;
        }
        return true;
    }


    //Logic to retrieve toll fee from Yaml file depending on time of travel
    public double getFee(LocalTime time) {
        return timeFeeList.stream()
                .filter(timeFee -> isMatched(timeFee, time))
                .findAny()
                .map(TimeIntervalFee::getFee)
                .orElse(0d);
    }

    private boolean isMatched(TimeIntervalFee timeFee, LocalTime time) {
        return withinStartTime(timeFee, time) && withinEndTime(timeFee, time);
    }

    private boolean withinStartTime(TimeIntervalFee timeFee, LocalTime time) {
        return (timeFee.getStartTime().equals(time) || timeFee.getStartTime().isBefore(time));
    }

    private boolean withinEndTime(TimeIntervalFee timeFee, LocalTime time) {
        return (timeFee.getEndTime().equals(time) || timeFee.getEndTime().isAfter(time));
    }

}