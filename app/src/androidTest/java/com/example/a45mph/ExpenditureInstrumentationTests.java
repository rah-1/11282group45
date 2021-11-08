package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
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
            InstrumentationTestHelper.exceptionHandler(e);
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
            InstrumentationTestHelper.exceptionHandler(e);
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
            InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testReadLog()
    {
        LocalDateTime thisInstant = LocalDateTime.now();
        VehicleProfile testCar = new VehicleProfile();
        ExpenditureDataLog exp = new ExpenditureDataLog(10.00,2, thisInstant, testCar);
        exp.setEntry();

        Scanner lineScanner = new Scanner(exp.entry);
        ExpenditureDataLog test = ExpenditureDataLog.readLog(lineScanner);

        assertEquals(exp.entry,test.entry);
    }


}
