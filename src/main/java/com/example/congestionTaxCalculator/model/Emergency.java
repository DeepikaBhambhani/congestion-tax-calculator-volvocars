package com.example.congestionTaxCalculator.model;

import static com.example.congestionTaxCalculator.util.Constants.EMERGENCY;

public class Emergency implements Vehicle {
    @Override
    public String getVehicle() {
        return EMERGENCY;
    }
}
