package com.example.a45mph;

import org.junit.Test;

import java.time.LocalDateTime;

public class TripTests
{
    @Test
    public void testConsumption()
    {
        TripLog test = new TripLog(50, 2.5);
        assert (test.getConsumption() == 2.5);
    }

    @Test
    public void testOdometer()
    {
        TripLog test = new TripLog(50, 2.5);
        assert (test.getOdometer() == 50);
    }

    @Test
    public void testMileageCalculation()
    {
        TripLog test = new TripLog(50, 2.5);
        assert (test.getMileage() == (50 / 2.5));
    }

    @Test
    public void testTripLogTiming()
    {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripLog test = new TripLog(50, 2.5, thisInstant);
        assert (thisInstant == test.getTime());
    }
}
