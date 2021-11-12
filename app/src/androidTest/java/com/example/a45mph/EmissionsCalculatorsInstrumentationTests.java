package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EmissionsCalculatorsInstrumentationTests {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testGetTripEmissions() {
        try {
            assert InstrumentationTestHelper.setUpTests(TripDataLog.FILEPATH);
            VehicleProfile vp = new VehicleProfile("Dodge","Charger","Mom's Car",2014,"1000",
                    new Fueltype("Regular"),444, LocalDateTime.now());
            VehicleSelectionActivity.profileAdapter.addProfile(vp);

            TripDataLog trip1 = new TripDataLog(100,5,LocalDateTime.now(),vp);
            TripDataLog trip2 = new TripDataLog(152, 15, LocalDateTime.now(),vp);

            assert EmissionsCalculator.getTripEmissions(trip1) == 444 * 100;
            assert EmissionsCalculator.getTripEmissions(trip2) == 444 * 152;

        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testGetTotalTripEmissions(){
        assert InstrumentationTestHelper.setUpTests(TripDataLog.FILEPATH);
        VehicleProfile vp = new VehicleProfile("Dodge","Charger","Mom's Car",
                2014,"1000",new Fueltype("Regular"),202,LocalDateTime.now());
        VehicleProfile vp1 = new VehicleProfile("Dodge","Ram F150", "Dad's Truck",
                2007,"1001",new Fueltype("Regular"), 577,LocalDateTime.now());

        VehicleSelectionActivity.profileAdapter.addProfile(vp);
        VehicleSelectionActivity.profileAdapter.addProfile(vp1);

        TripDataLog trip1 = new TripDataLog(100,5,LocalDateTime.now(),vp);
        TripDataLog trip2 = new TripDataLog(152, 15, LocalDateTime.now(),vp);
        TripDataLog trip3 = new TripDataLog(394,13, LocalDateTime.now(), vp1);
        TripDataLog trip4 = new TripDataLog(495, 14, LocalDateTime.now(), vp1);
        trip1.setEntry();
        trip2.setEntry();
        trip3.setEntry();
        trip4.setEntry();

        assertEquals(EmissionsCalculator.getTotalEmissions(new Scanner(trip1.entry + trip2.entry + trip3.entry + trip4.entry),vp),(202*100)+(202*152),0);
        assertEquals(EmissionsCalculator.getTotalEmissions(new Scanner(trip1.entry + trip2.entry + trip3.entry + trip4.entry),vp1), (577*394)+(577*495),0);
    }

    @Test
    public void testGetAverageEmissions() {
        VehicleProfile vp = new VehicleProfile("Dodge","Ram F150", "Dad's Truck",
                2007,"1001",new Fueltype("Regular"), 577,LocalDateTime.now());

        VehicleSelectionActivity.profileAdapter.addProfile(vp);

        TripDataLog trip1 = new TripDataLog(152, 15, LocalDateTime.now(),vp);
        TripDataLog trip2 = new TripDataLog(394,13, LocalDateTime.now(), vp);
        TripDataLog trip3 = new TripDataLog(495, 14, LocalDateTime.now(), vp);
        trip1.setEntry();
        trip2.setEntry();
        trip3.setEntry();

        Scanner testScanner = new Scanner(trip1.entry + trip2.entry + trip3.entry);

        assertEquals(EmissionsCalculator.getAverageEmissions(testScanner,vp),(152+394+495)*577/3.0, 0);
    }
}