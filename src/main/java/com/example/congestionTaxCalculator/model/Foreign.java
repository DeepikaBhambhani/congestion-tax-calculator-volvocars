package com.example.congestionTaxCalculator.model;

import static com.example.congestionTaxCalculator.util.Constants.FOREIGN;

public class Foreign implements Vehicle{
    @Override
    public String getVehicle() {
        return FOREIGN;
    }
}
