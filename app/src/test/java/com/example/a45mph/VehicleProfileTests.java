package com.example.a45mph;

import org.junit.Test;

import java.io.IOException;

public class VehicleProfileTests {


    @Test
    public void testVehicleProfileCreation()
    {
        double result = 0;
        try {
            result = FuelCostActivity.calculateCost(2.5,30,false);

            assert (result == 75);
            assert (FuelCalculators.getFuelExpenditure().size() == 1);
        } catch (IOException e) {
            assert false;
        }

    }


}
