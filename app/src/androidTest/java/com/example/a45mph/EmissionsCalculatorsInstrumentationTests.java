package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class EmissionsCalculatorsInstrumentationTests {

    private Context appContext;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
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

    @Test
    public void testGetAllEmissions() {

        try {
            assert InstrumentationTestHelper.setUpTests(TripDataLog.FILEPATH);

            LocalDateTime instant1 = LocalDateTime.now();
            LocalDateTime instant2 = LocalDateTime.now();

            VehicleProfile vp = new VehicleProfile("Dodge", "Ram F150", "Dad's Truck",
                    2007, "1001", new Fueltype("Regular"), 577, instant1);
            VehicleProfile vp1 = new VehicleProfile("Volkswagen", "Beetle", "Dream Car",
                    1968, "1", new Fueltype("Diesel"), 201, instant2);

            TripDataLog trip1 = new TripDataLog(100,5,instant1,vp);
            TripDataLog trip2 = new TripDataLog(152, 15, instant1,vp1);
            TripDataLog trip3 = new TripDataLog(394,13, instant2, vp);
            TripDataLog trip4 = new TripDataLog(495, 14, instant2, vp1);
            TripDataLog[] trips = {trip1, trip2, trip3, trip4};

            for (TripDataLog t : trips)
            {
                t.transfer();
            }

            useAppContext();
            ArrayList<EmissionDataLog> emits = EmissionsCalculator.getAllEmissions(new Scanner(appContext.getFileStreamPath("tripLog.csv")));

            EmissionDataLog[] emitTests = {
                    new EmissionDataLog(trip1.getOdometer()*trip1.getVehicle().getCO2(),trip1.getTime(),trip1.getVehicle()),
                    new EmissionDataLog(trip2.getOdometer()*trip2.getVehicle().getCO2(),trip2.getTime(),trip2.getVehicle()),
                    new EmissionDataLog(trip3.getOdometer()*trip3.getVehicle().getCO2(),trip3.getTime(),trip3.getVehicle()),
                    new EmissionDataLog(trip4.getOdometer()*trip4.getVehicle().getCO2(),trip4.getTime(),trip4.getVehicle()) };

            int i = 0;
            for (EmissionDataLog e : emits)
            {
                assertEquals(e.getVehicle().getName(),emitTests[i++].getVehicle().getName());
            }

        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }

    }
}
