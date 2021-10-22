package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TripRecordingInstrumentationTests {
    private final String FILEPATH = "/data/data/com.example.a45mph/tripLog.csv";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testDataInputAndRecording() {

        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            assertEquals(RecordTripActivity.recordTrip(20, 4, thisInstant), 5,0); // that's some pretty bad mileage

            Scanner s = new Scanner(new File(FILEPATH));
            assert s.hasNextLine();

        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testAccurateRecording() {
        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            assertEquals(RecordTripActivity.recordTrip(60, 4, thisInstant), 15,0);

            Scanner s = new Scanner(new File(FILEPATH));
            TripDataLog trip = new TripDataLog(60,4,thisInstant);
            assert InstrumentationTestHelper.testTransfer(trip,s);
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testDataValidationWithZero() {
        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            double result = RecordTripActivity.recordTrip(0,0);
            assertEquals(result, 0, 0);

            Scanner s = new Scanner(new File(FILEPATH));
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testDataValidationWithNegatives()
    {
        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            double result = RecordTripActivity.recordTrip(-3,-1);
            assertEquals(result, 0, 0);

            Scanner s = new Scanner(new File(FILEPATH));
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testMultipleRecords() {
        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            assertEquals(RecordTripActivity.recordTrip(60, 4, thisInstant), 15,0);
            assertEquals(RecordTripActivity.recordTrip(120,10,thisInstant), 12, 0);


            Scanner s = new Scanner(new File(FILEPATH));
            TripDataLog trip1 = new TripDataLog(60,4,thisInstant);
            TripDataLog trip2 = new TripDataLog(120, 10, thisInstant);

            assert InstrumentationTestHelper.testTransfer(trip1,s);
            assert InstrumentationTestHelper.testTransfer(trip2,s);
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }
}
