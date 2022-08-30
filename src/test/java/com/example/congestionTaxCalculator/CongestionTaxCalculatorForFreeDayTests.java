package com.example.congestionTaxCalculator;


import com.example.congestionTaxCalculator.model.*;
import com.example.congestionTaxCalculator.controller.CongestionTaxCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CongestionTaxCalculatorForFreeDayTests {

    private static CongestionTaxCalculator tollCalculator;
    private static LocalDate date;
    private static Vehicle car;


    @BeforeAll
    private static void initDate() {
        tollCalculator= new CongestionTaxCalculator(new CongestionTaxCalculatorServiceImpl());
        car = new Car();
    }

    @Test
    @DisplayName("Calculating Toll on weekends")
     public void weekendTollTest(){
        List<LocalDateTime> dates = new ArrayList<>();
        date = LocalDate.of(2022, 7, 31);
        dates.add(LocalDateTime.of(date, LocalTime.of(12,52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(13,52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(14,52)));
        assertEquals(0,tollCalculator.getTollFee(car,dates));
    }

    @Test
    @DisplayName("Test for toll free vehicles")
     public void tollFreeVehicles() {

        List<LocalDateTime> dateList=new ArrayList<>();
        date = LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth());
        dateList.add(LocalDateTime.of(date, LocalTime.of(12,52)));
        dateList.add(LocalDateTime.of(date, LocalTime.of(13,52)));
        dateList.add(LocalDateTime.of(date, LocalTime.of(14,52)));
        Vehicle  emergency=new Emergency();
        Vehicle  diplomat=new Diplomat();
        Vehicle  foreign=new Foreign();
        Vehicle  military=new Military();
        Vehicle  motorbike=new Motorbike();
        Vehicle  tractor=new Tractor();
        assertEquals(0,  tollCalculator.getTollFee(diplomat, dateList));
        assertEquals(0,  tollCalculator.getTollFee(foreign, dateList));
        assertEquals(0,  tollCalculator.getTollFee(military, dateList));
        assertEquals(0,  tollCalculator.getTollFee(motorbike, dateList));
        assertEquals(0,  tollCalculator.getTollFee(tractor, dateList));
        assertEquals(0,  tollCalculator.getTollFee(emergency, dateList));

    
    }

    @Test
    @DisplayName("Toll calculation on toll free days")
   public void freeDateTollTest() {
        int year=LocalDate.now().getYear();
        date = LocalDate.of(2022, 01, 01);
        List<LocalDateTime> dateList=new ArrayList<>();
        dateList.add(LocalDateTime.of(date,LocalTime.of(12,52)));
        dateList.add(LocalDateTime.of(date,LocalTime.of(15,52)));
        assertEquals(0,  tollCalculator.getTollFee(car, dateList));

    }


}
