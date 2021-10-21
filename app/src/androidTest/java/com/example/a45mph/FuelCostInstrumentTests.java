package com.example.a45mph;

import static org.junit.Assert.assertEquals;
import android.content.Context;
import android.os.Looper;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.lifecycle.LifecycleRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class FuelCostInstrumentTests {

    private final String FILEPATH = "/data/data/com.example.a45mph/expendLog.csv";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testCalculateCostWithTransfer() {
        try
        {
            double result = FuelCostActivity.calculateCost(2.5,50,true);
            assert (result == 125.00);
            assert FuelCalculators.getFuelExpenditure().isEmpty();
        } catch (Exception e) {
            assert false;
        }

    }

    @Test
    public void testExpenditureLogging() {
        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            ExpenditureDataLog expenditureDataLog = new ExpenditureDataLog(125.00,50,LocalDateTime.now());

            // essentially emulates the transferLogs() function of FuelCalculators class
            expenditureDataLog.transfer();

            Scanner s = new Scanner(new File(FILEPATH));
            assert InstrumentationTestHelper.testTransfer(expenditureDataLog,s);
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testLoggingMultipleExpenditures() {
        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            ExpenditureDataLog expenditureDataLog1 = new ExpenditureDataLog(125.00,50,LocalDateTime.now());
            ExpenditureDataLog expenditureDataLog2 = new ExpenditureDataLog(60.00, 30,LocalDateTime.now());

            // essentially emulates the transferLogs() function of FuelCalculators class
            expenditureDataLog1.transfer();
            expenditureDataLog2.transfer();

            Scanner s = new Scanner(new File(FILEPATH));
            assert InstrumentationTestHelper.testTransfer(expenditureDataLog1,s);
            assert InstrumentationTestHelper.testTransfer(expenditureDataLog2,s);
            assert !s.hasNextLine();
        } catch (Exception e)
        {
            assert false;
        }
    }

    @Test
    public void testCalculationAndLogging() {
        try
        {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);
            double result1 = FuelCostActivity.calculateCost(2.5,50,false);
            assert (result1 == 125.00);
            double result2 = FuelCostActivity.calculateCost(2.0, 30, false);
            assert (result2 == 60.00);
            assert (FuelCalculators.getFuelExpenditure().size() == 2);

            // save the multiple logs before transferring them. The inference is that the effect should
            // be the same as letting the second call to calculateCost do the transfer
            ArrayList<ExpenditureDataLog> fuelExpenditures = FuelCalculators.getFuelExpenditure();
            FuelCalculators.transferLogs();
            assert (FuelCalculators.getFuelExpenditure().isEmpty());

            Scanner s = new Scanner(new File(FILEPATH));
            assert InstrumentationTestHelper.testTransfer(fuelExpenditures.get(0),s);
            assert InstrumentationTestHelper.testTransfer(fuelExpenditures.get(1),s);
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert false;
        }
    }
}
