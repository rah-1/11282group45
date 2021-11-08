package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenditureInstrumentationTests {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testExpenditureRecording1() {

        try {
            InstrumentationTestHelper.setUpFile(ExpenditureDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            ExpenditureDataLog log = new ExpenditureDataLog(10.00,22,thisInstant,testCar);
            log.transfer();

            Scanner s = new Scanner(new File(ExpenditureDataLog.FILEPATH));
            InstrumentationTestHelper.testTransfer(log,s);
            assert !s.hasNextLine();

        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }

    }

    @Test
    public void testExpenditureRecording2() {

        try {
            InstrumentationTestHelper.setUpFile(ExpenditureDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            ExpenditureDataLog log = new ExpenditureDataLog(10.00,22,thisInstant,testCar);
            ExpenditureDataLog log1 = new ExpenditureDataLog(23.94,12,thisInstant,testCar);
            log.transfer();
            log1.transfer();

            Scanner s = new Scanner(new File(ExpenditureDataLog.FILEPATH));
            InstrumentationTestHelper.testTransfer(log,s);
            InstrumentationTestHelper.testTransfer(log1,s);
            assert !s.hasNextLine();

        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testExpenditureRecording3() {

        try {
            InstrumentationTestHelper.setUpFile(ExpenditureDataLog.FILEPATH);
            LocalDateTime thisInstant = LocalDateTime.now();
            VehicleProfile testCar = new VehicleProfile();
            ExpenditureDataLog log = new ExpenditureDataLog(10.00,22,thisInstant,testCar);
            ExpenditureDataLog log1 = new ExpenditureDataLog(23.94,12,thisInstant,testCar);

            assertEquals(log.getExpenditure(),10.00,0);
            assertEquals(log.getAmountBought(), 22.00, 0);
            assertEquals(log.getTime(), thisInstant);
            assertEquals(log.getVehicle().getName(), testCar.getName());

        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testReadLog() {
        LocalDateTime thisInstant = LocalDateTime.now();
        VehicleProfile testCar = new VehicleProfile("Test","Test","Test");
        VehicleSelectionActivity.profileAdapter = new VehicleProfileAdapter();
        VehicleSelectionActivity.profileAdapter.addProfile(testCar);
        ExpenditureDataLog exp = new ExpenditureDataLog(10.00,2, thisInstant, testCar);
        exp.setEntry();

        Scanner lineScanner = new Scanner(exp.entry);
        lineScanner.useDelimiter(",");
        ExpenditureDataLog test = ExpenditureDataLog.readLog(lineScanner);
        test.setEntry();

        assertEquals(exp.entry,test.entry);
    }

    @Test
    public void testLoadExpenditures() {
        try {
            assert InstrumentationTestHelper.setUpTests(ExpenditureDataLog.FILEPATH);
            VehicleProfile testCar = new VehicleProfile("Test","Test","Test");
            VehicleSelectionActivity.profileAdapter.addProfile(testCar);

            ExpenditureDataLog exp = new ExpenditureDataLog(10.00, 2, LocalDateTime.now(),testCar);
            ExpenditureDataLog exp1 = new ExpenditureDataLog(24.00, 12, LocalDateTime.now(),testCar);
            ExpenditureDataLog exp2 = new ExpenditureDataLog(432.42, 1, LocalDateTime.now(), testCar);
            exp.setEntry();
            exp1.setEntry();
            exp2.setEntry();

            ArrayList<ExpenditureDataLog> list = ExpenditureDataLog.loadExpenditureDataLogs();

            for (ExpenditureDataLog e : list)
                e.setEntry();

            assertEquals(exp.entry,list.get(0).entry);
            assertEquals(exp1.entry,list.get(1).entry);
            assertEquals(exp2.entry,list.get(2).entry);
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

}
