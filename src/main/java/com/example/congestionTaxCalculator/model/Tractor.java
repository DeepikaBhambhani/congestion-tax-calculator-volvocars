package com.example.congestionTaxCalculator.model;

import static com.example.congestionTaxCalculator.util.Constants.TRACTOR;

public class Tractor implements Vehicle{

    @Override
    public String getVehicle() {
        return TRACTOR;
    }
}
