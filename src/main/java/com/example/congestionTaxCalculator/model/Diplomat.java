package com.example.congestionTaxCalculator.model;

import static com.example.congestionTaxCalculator.util.Constants.DIPLOMAT;

public class Diplomat implements Vehicle{
    @Override
    public String getVehicle() {
        return DIPLOMAT;
    }
}
