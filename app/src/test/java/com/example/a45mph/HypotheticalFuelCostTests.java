package com.example.a45mph;

import org.junit.Test;

public class HypotheticalFuelCostTests {
    @Test
    public void executeHypotheticalFuelCost()
    {
        assert (7 * 42 == FuelCalculators.hypotheticalFuelCost(7.00, 42.00));
        assert (12 * 48 == FuelCalculators.hypotheticalFuelCost(12.00, 48.00)); // Yikes! $12 per gallon
        assert (2.5 * 5.6 == FuelCalculators.hypotheticalFuelCost(2.50, 5.60));
    }

    @Test
    public void testRounding()
    {
        assert (0.13 == FuelCalculators.hypotheticalFuelCost(1, 0.125));
        assert (0.13 == FuelCalculators.hypotheticalFuelCost(0.125, 1));
    }

    @Test
    public void testHypotheticalFuelCostWithZero()
    {
        for (int i = 1; i < 10; i++)
        {
            assert (0 == FuelCalculators.hypotheticalFuelCost(i,0));
        }
    }

    @Test
    public void testHypotheticalFuelCostWithNegativeArguments()
    {
        // essentially a repeat of the negative testing for hypotheticalBuyableFuel()
        try
        {
            // this will throw an arithmetic exception
            double ensure1 = FuelCalculators.hypotheticalFuelCost(-1,1);
            double ensure2 = FuelCalculators.hypotheticalFuelCost(1,-1);
            double ensure3 = FuelCalculators.hypotheticalFuelCost(-1,-1);
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
