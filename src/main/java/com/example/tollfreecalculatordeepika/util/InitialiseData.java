package com.example.tollfreecalculatordeepika.util;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tollfreecalculatordeepika.util.Constants.TIME_FEE_TO_PAY_YML;

public class InitialiseData {
    public static final Map<LocalDate,Boolean> holidayMap=new HashMap<>();
    public static final List<TimeIntervalFee> timeFeeList=new ArrayList<>();

    static {
        initialiseHolidayMap();
        initTimeFeeYaml();
    }

    public static void initialiseHolidayMap(){
        int year= LocalDate.now().getYear();

        holidayMap.put(LocalDate.of(year,1,1),true);
        holidayMap.put(LocalDate.of(year,1,6),true);

        holidayMap.put(LocalDate.of(year,4,9),true);
        holidayMap.put(LocalDate.of(year,4,10),true);
        holidayMap.put(LocalDate.of(year,4,13),true);
        holidayMap.put(LocalDate.of(year,4,30),true);

        holidayMap.put(LocalDate.of(year,5,1),true);
        holidayMap.put(LocalDate.of(year,5,20),true);
        holidayMap.put(LocalDate.of(year,5,21),true);

        holidayMap.put(LocalDate.of(year,6,5),true);
        holidayMap.put(LocalDate.of(year,6,19),true);

        for(int i=1;i<=31;i++){
            holidayMap.put(LocalDate.of(year,7,i),true);
        }

        holidayMap.put(LocalDate.of(year,10,30),true);

        holidayMap.put(LocalDate.of(year,12,24),true);
        holidayMap.put(LocalDate.of(year,12,25),true);

        holidayMap.put(LocalDate.of(year,12,31),true);

    }

    public static void initTimeFeeYaml() {

        try (InputStream in = InitialiseData.class.getResourceAsStream(TIME_FEE_TO_PAY_YML)) {
            Yaml yaml = new Yaml(new Constructor(TimeIntervalFeeList.class));
            TimeIntervalFeeList list = yaml.load(in);

            list.getTimeFeeList()
                    .stream()
                    .map(timeFeeObj -> timeFeeToDto(timeFeeObj))
                    .forEach(timeFeeList::add);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static TimeIntervalFee timeFeeToDto(TimeIntervalFeeList.TimeFeeObj timeFeeObj) {
        return new TimeIntervalFee()
                .setStartTime(LocalTime.parse(timeFeeObj.getStart()))
                .setEndTime(LocalTime.parse(timeFeeObj.getEnd()))
                .setFee(timeFeeObj.getFee());
    }

}
