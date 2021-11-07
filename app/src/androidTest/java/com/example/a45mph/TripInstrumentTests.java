package com.example.a45mph;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class TripInstrumentTests {
    private final String FILEPATH = "/data/data/com.example.a45mph/tripLog.csv";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testTripLogIO() {
        LocalDateTime thisInstant = LocalDateTime.now();
        VehicleProfile testCar = new VehicleProfile("Test","Test","Test");
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant, testCar);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant, testCar);

        assert InstrumentationTestHelper.setUpFile(FILEPATH);

        try {
            testTrip1.transfer();
            testTrip2.transfer();

            Scanner s = new Scanner(new File(FILEPATH));

            assert InstrumentationTestHelper.testTransfer(testTrip1,s);
            assert InstrumentationTestHelper.testTransfer(testTrip2,s);
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert false;
        }
    }



}
