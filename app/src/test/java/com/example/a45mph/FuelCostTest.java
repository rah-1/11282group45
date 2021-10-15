package com.example.a45mph;

import org.junit.Test;


public class FuelCostTest {
    @Test
    public void executeFuelCost()
    {
        assert (5 * 30 == FuelCalculators.fuelCost(5, 30));
        assert (7.9 * 15 == FuelCalculators.fuelCost(7.9, 15));
        assert (17.5 * 20 == FuelCalculators.fuelCost(17.5, 20));
    }

    @Test
    public void testFuelCostRounding(){
        assert (0.26 == FuelCalculators.fuelCost(1, 0.257));
        assert (0.26 == FuelCalculators.fuelCost(0.257, 1));
    }

    @Test
    public void testFuelCostZeros(){
        assert (0 == FuelCalculators.fuelCost(4, 0));
        assert (0 == FuelCalculators.fuelCost(9, 0));
        assert (0 == FuelCalculators.fuelCost(69, 0));
    }

    @Test
    public void testFuelCostWithNegativeArguments()
    {
        // essentially a repeat of the negative testing for hypotheticalBuyableFuel()
        try
        {
            // this will throw an arithmetic exception
            double ensure1 = FuelCalculators.fuelCost(-1,1);
            double ensure2 = FuelCalculators.fuelCost(1,-1);
            double ensure3 = FuelCalculators.fuelCost(-1,-1);
            assert false; // fail the case if you get here

        } catch (ArithmeticException e)
        {
            assert true; // ArithmeticException caught. Pass test
        } catch (Exception f)
        {
            assert false; // fail bc another exception occurred
        }
    }
}
