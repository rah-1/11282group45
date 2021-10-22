package com.example.a45mph;

import org.junit.Test;

public class HypotheticalBuyableFuelTests {
    @Test
    public void executeHypotheticalBuyableFuel()
    {
        assert (3.00 == FuelCalculators.hypotheticalBuyableFuel(3.00, 9.00));
        assert (4.00 == FuelCalculators.hypotheticalBuyableFuel(3.00, 12.00));
        assert (2.50 == FuelCalculators.hypotheticalBuyableFuel(2.00, 5.00));
    }

    @Test
    public void testHypotheticalBuyableFuelWithZero()
    {
        for (int i = 1; i < 10; i++)
        {
            assert (0 == FuelCalculators.hypotheticalBuyableFuel(i,0));
        }

        try
        {
            // this will throw an arithmetic exception
            double ensureFuncRuns = FuelCalculators.hypotheticalBuyableFuel(0,0);
            assert false; // fail the case if you get here

        } catch (ArithmeticException e)
        {
            assert true; // ArithmeticException caught. Pass test
        } catch (Exception f)
        {
            assert false; // fail bc another exception occured
        }
    }

    @Test
    public void testHypotheticalBuyableFuelWithNegativeArguments()
    {
        try
        {
            // this will throw an arithmetic exception
            double ensure3 = FuelCalculators.hypotheticalBuyableFuel(-1,-1);
            assert false; // fail the case if you get here

        } catch (ArithmeticException e)
        {
            assert true; // ArithmeticException caught. Pass test
        } catch (Exception f)
        {
            assert false; // fail bc another exception occured
        }
    }

}
