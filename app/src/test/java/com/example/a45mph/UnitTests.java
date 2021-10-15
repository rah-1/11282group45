package com.example.a45mph;

import org.junit.Test;
import org.junit.Assert.*;
import java.nio.channels.ScatteringByteChannel;

public class UnitTests {
    @Test
    private void executeHypotheticalBuyableFuel()
    {
        assert (3.00 == BuyableFuel.hypotheticalBuyableFuel(3.00, 9.00));
        assert (4.00 == BuyableFuel.hypotheticalBuyableFuel(3.00, 12.00));
        assert (2.50 == BuyableFuel.hypotheticalBuyableFuel(2.50, 5.00));
    }

    @Test
    private void testHypotheticalBuyableFuelWithZero()
    {
        for (int i = 0; i < 9; i++)
        {
            assert (0 == BuyableFuel.hypotheticalBuyableFuel(i,0));
        }

        try
        {
            // this will throw an arithmetic exception
            double ensureFuncRuns = BuyableFuel.hypotheticalBuyableFuel(0,0);
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
    private void testHypotheticalBuyableFuelWithNegativeArguments()
    {
        try
        {
            // this will throw an arithmetic exception
            double ensure1 = BuyableFuel.hypotheticalBuyableFuel(-1,1);
            double ensure2 = BuyableFuel.hypotheticalBuyableFuel(1,-1);
            double ensure3 = BuyableFuel.hypotheticalBuyableFuel(-1,-1);
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
