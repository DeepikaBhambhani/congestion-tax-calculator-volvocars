package com.example.congestionTaxCalculator.model;


import static com.example.congestionTaxCalculator.util.Constants.MILITARY;

public class Military implements Vehicle {
    @Override
    public String getVehicle(){
        return MILITARY;
    }

}
