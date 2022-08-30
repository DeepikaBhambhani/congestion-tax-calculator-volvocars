package com.example.congestionTaxCalculator.model;

import static com.example.congestionTaxCalculator.util.Constants.CAR;

public class Car implements Vehicle {
    @Override
    public String getVehicle() {
        return CAR;
    }
}
