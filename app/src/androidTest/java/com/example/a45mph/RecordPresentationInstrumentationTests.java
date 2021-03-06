package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
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
            InstrumentationTestHelper.setUpTests(TripDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

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
            assert (tripList.size() == 5);

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
            InstrumentationTestHelper.setUpTests(ExpenditureDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

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
            ArrayList<ExpenditureDataLog> expList = adapter.getExpenditures();
            assert (expList.size() == 5);

            int i = 0;
            for (ExpenditureDataLog e : expList)
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
    public void testAddingTrip() {
        try {
            assert InstrumentationTestHelper.setUpTests(TripDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

            TripDataLog trip1 = new TripDataLog(10,42, thisInstant, testCar);
            TripDataLog trip2 = new TripDataLog(10, 23, thisInstant, testCar);
            TripDataLog trip3 = new TripDataLog(49, 334, thisInstant,testCar);
            TripDataLog trip4 = new TripDataLog(38.44,134.5, thisInstant, testCar);
            TripDataLog trip5 = new TripDataLog(902, 33, thisInstant, testCar);
            TripDataLog list[] = {trip1,trip2,trip3,trip4,trip5};

            // The description of this two adapter test is similar to the description provided for
            // the expenditure tests below.
            TripDataLogAdapter tripAdapter = new TripDataLogAdapter();

            for (TripDataLog t : list)
            {
                RecordTripActivity.recordTrip(t.getOdometer(),t.getConsumption(),t.getTime(),t.getVehicle());
                t.setEntry();
            }

            TripDataLogAdapter tripAdapter1 = new TripDataLogAdapter();
            ArrayList<TripDataLog> list2 = tripAdapter1.getTrips();
            assert (list2.size() == 5);

            assert tripAdapter.getTrips().isEmpty();

            int i = 0;
            for (TripDataLog t : list2)
            {
                t.setEntry();
                assertEquals(t.entry,list[i++].entry);
            }

        } catch (ArithmeticException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testAddingExpenditure() {
        try {
            assert InstrumentationTestHelper.setUpTests(ExpenditureDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

            ExpenditureDataLog exp1 = new ExpenditureDataLog(10,42, thisInstant, testCar);
            ExpenditureDataLog exp2 = new ExpenditureDataLog(10, 23, thisInstant, testCar);
            ExpenditureDataLog exp3 = new ExpenditureDataLog(49, 334, thisInstant,testCar);
            ExpenditureDataLog exp4 = new ExpenditureDataLog(38.44,134.5, thisInstant, testCar);
            ExpenditureDataLog exp5 = new ExpenditureDataLog(902, 33, thisInstant, testCar);
            ExpenditureDataLog list[] = {exp1,exp2,exp3,exp4,exp5};

            // first adapter represents the list displayed before recording the above fuel expenditures
            ExpenditureDataLogAdapter expendAdapter = new ExpenditureDataLogAdapter();

            for (ExpenditureDataLog e : list)
            {
                FuelCalculators.fuelCost(e.getExpenditure(),e.getAmountBought(),e.getTime(),e.getVehicle());
                e.transfer();
            }

            // second adapter represents the list displayed after recording expenditures
            ExpenditureDataLogAdapter expendAdapter1 = new ExpenditureDataLogAdapter();
            ArrayList<ExpenditureDataLog> list2 = expendAdapter1.getExpenditures();
            assert (list2.size() == 5);

            // the list before recording should be empty.
            assert expendAdapter.getExpenditures().isEmpty();

            // the list after has the five records in it.
            int i = 0;
            for (ExpenditureDataLog e : list2)
            {
                e.setEntry();
                assertEquals(e.entry,list[i++].entry);
            }

        } catch (ArithmeticException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testTripQuery() {

        try {
            assert InstrumentationTestHelper.setUpTests(TripDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

            TripDataLog trip1 = new TripDataLog(10,42, thisInstant, testCar);
            trip1.transfer();

            TripDataLogAdapter tripDataLogAdapter = new TripDataLogAdapter();
            TripDataLog test = tripDataLogAdapter.getTrip(0);
            test.setEntry();

            assertEquals(trip1.entry,test.entry);

        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }

    }

    @Test
    public void testExpendQuery() {

        try {
            assert InstrumentationTestHelper.setUpTests(ExpenditureDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

            ExpenditureDataLog exp1 = new ExpenditureDataLog(10,42, thisInstant, testCar);
            exp1.transfer();

            ExpenditureDataLogAdapter expenditureDataLogAdapter = new ExpenditureDataLogAdapter();
            ExpenditureDataLog test = expenditureDataLogAdapter.getExpenditure(0);
            test.setEntry();

            assertEquals(exp1.entry,test.entry);

        } catch (IOException e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

}
