package com.example.a45mph;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class TripTests
{
    @Test
    public void testConsumption()
    {
        TripDataLog test = new TripDataLog(50, 2.5);
        assert (test.getConsumption() == 2.5);
    }

    @Test
    public void testOdometer()
    {
        TripDataLog test = new TripDataLog(50, 2.5);
        assert (test.getOdometer() == 50);
    }

    @Test
    public void testMileageCalculation()
    {
        TripDataLog test = new TripDataLog(50, 2.5);
        assert (test.getMileage() == (50 / 2.5));
    }

    @Test
    public void testTripLogTiming()
    {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripDataLog test = new TripDataLog(50, 2.5, thisInstant);
        assert (thisInstant == test.getTime());
    }

    @Test
    public void testTripLogTransfer() throws IOException {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant);

        testTrip1.transfer();
        testTrip2.transfer();


        BufferedReader testBR = new BufferedReader(new FileReader("/data/data/com.example.a45mph/tripLog.csv"));
        String testResult;
        String got = "";
        while((testResult = testBR.readLine()) != null){
            got = testResult;
        }
        String expected = testTrip2.getTime() + "," + testTrip2.getConsumption() + "," + testTrip2.getOdometer() + "," + testTrip2.getMileage();
        assert(Objects.equals(got, expected));
    }
}
