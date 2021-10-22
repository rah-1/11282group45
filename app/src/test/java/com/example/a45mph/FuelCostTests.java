package com.example.a45mph;

import org.junit.Test;

import java.io.IOException;

public class FuelCostTests {
    @Test
    public void executeFuelCost()
    {
        assert (5 * 30 == FuelCalculators.fuelCost(5, 30));
        assert (7.9 * 15 == FuelCalculators.fuelCost(7.9, 15));
        assert (17.5 * 20 == FuelCalculators.fuelCost(17.5, 20));
        assert (FuelCalculators.getFuelExpenditure().size() == 3);
        FuelCalculators.clearExpenditure();
    }

    @Test
    public void testFuelCostRounding(){
        assert (0.26 == FuelCalculators.fuelCost(1, 0.257));
        assert (0.26 == FuelCalculators.fuelCost(0.257, 1));
        assert (FuelCalculators.getFuelExpenditure().size() == 2);
        FuelCalculators.clearExpenditure();
    }

    @Test
    public void testFuelCostWithZero(){
        assert (0 == FuelCalculators.fuelCost(4, 0));
        assert (0 == FuelCalculators.fuelCost(9, 0));
        assert (0 == FuelCalculators.fuelCost(69, 0));
        assert (FuelCalculators.getFuelExpenditure().size() == 3);
        FuelCalculators.clearExpenditure();
    }

    @Test
    public void testFuelCostWithNegativeArguments()
    {
        try
        {
            // this will throw an arithmetic exception
            double ensure1 = FuelCalculators.fuelCost(-1,-1);
            assert false; // fail the case if you get here

        } catch (ArithmeticException e)
        {
            // check that the fuel transaction was not
            assert (FuelCalculators.getFuelExpenditure().size() == 0);
            assert true; // ArithmeticException caught and size the same. Pass test
        } catch (Exception f)
        {
            assert false; // fail bc another exception occurred
        }
    }

    @Test
    public void testCorrectDataInput()
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

    @Test
    public void testBadDataInput()
    {
        try {
            double result = FuelCostActivity.calculateCost(-2.5, -30, false);
            assert false;
        } catch (ArithmeticException e) {
            assert (FuelCalculators.getFuelExpenditure().size() == 0);
        } catch (Exception f) {
            assert false;
        }
    }
}
