package com.example.congestionTaxCalculator.model;

import static com.example.congestionTaxCalculator.util.Constants.MOTORBIKE;

public class Motorbike implements Vehicle {
    @Override
    public String getVehicle() {
        return MOTORBIKE;
    }
}
