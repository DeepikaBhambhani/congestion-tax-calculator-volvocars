package com.example.congestionTaxCalculator.controller;

import com.example.congestionTaxCalculator.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CongestionTaxCalculatorService {
    boolean isTollFreeVehicle(Vehicle vehicle);
    boolean isTollFreeDate(LocalDate date);
    boolean isValid(Vehicle vehicle, List<LocalDateTime> dates);
}
