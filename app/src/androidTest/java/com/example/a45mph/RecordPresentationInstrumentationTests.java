package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RecordPresentationInstrumentationTests {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testTripReading() {

        try {
            InstrumentationTestHelper.setUpFile(TripDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();

            TripDataLog trip1 = new TripDataLog(10,42, thisInstant, testCar);
            TripDataLog trip2 = new TripDataLog(10, 23, thisInstant, testCar);
            TripDataLog trip3 = new TripDataLog(49, 334, thisInstant,testCar);
            TripDataLog trip4 = new TripDataLog(38.44,134.5, thisInstant, testCar);
            TripDataLog trip5 = new TripDataLog(902, 33, thisInstant, testCar);
            TripDataLog list[] = {trip1,trip2,trip3,trip4,trip5};

            for (TripDataLog t : list)
            {
                t.transfer();
            }

            TripDataLogAdapter adapter = new TripDataLogAdapter();
            ArrayList<TripDataLog> tripList = adapter.getTrips();

            int i = 0;
            for (TripDataLog t : tripList)
            {
                t.transfer();
                assertEquals(t.entry,list[i++].entry);
            }

        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testExpendReading() {
        try {
            InstrumentationTestHelper.setUpFile(TripDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();

            ExpenditureDataLog exp1 = new ExpenditureDataLog(10,42, thisInstant, testCar);
            ExpenditureDataLog exp2 = new ExpenditureDataLog(10, 23, thisInstant, testCar);
            ExpenditureDataLog exp3 = new ExpenditureDataLog(49, 334, thisInstant,testCar);
            ExpenditureDataLog exp4 = new ExpenditureDataLog(38.44,134.5, thisInstant, testCar);
            ExpenditureDataLog exp5 = new ExpenditureDataLog(902, 33, thisInstant, testCar);
            ExpenditureDataLog list[] = {exp1,exp2,exp3,exp4,exp5};

            for (ExpenditureDataLog e : list)
            {
                e.transfer();
            }

            ExpenditureDataLogAdapter adapter = new ExpenditureDataLogAdapter();
            ArrayList<ExpenditureDataLog> tripList = adapter.getExpenditures();

            int i = 0;
            for (ExpenditureDataLog e : tripList)
            {
                e.transfer();
                assertEquals(e.entry,list[i++].entry);
            }

        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testAddTrip() {

    }

    @Test
    public void testAddExpend() {

    }

    @Test
    public void testTripQuery() {

    }

    @Test
    public void testExpendQuery() {

    }

}
