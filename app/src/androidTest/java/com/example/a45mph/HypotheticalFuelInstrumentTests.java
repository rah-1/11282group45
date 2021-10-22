package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class HypotheticalFuelInstrumentTests {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testHypotheticalFuelCalculation() {
        double result = HypotheticalBuyableFuelActivity.calculateHypotheticalBuyableFuel(2.5,50);
        assert (result == 20);
    }

    @Test
    public void testHypotheticalFuelCalculationWithZero() {
        try {
            double result = HypotheticalBuyableFuelActivity.calculateHypotheticalBuyableFuel(0, 60);
            double result2 = HypotheticalBuyableFuelActivity.calculateHypotheticalBuyableFuel(2,0);
            assert (result2 == 0);
            assert (result == -1);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testOutputRounding() {
        double result = HypotheticalBuyableFuelActivity.calculateHypotheticalBuyableFuel(3,100);
        assert (result == 33.33);
    }

}
