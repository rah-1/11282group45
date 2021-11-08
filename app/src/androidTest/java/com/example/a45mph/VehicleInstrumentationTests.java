package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class VehicleInstrumentationTests {
    private final String FILEPATH = "/data/data/com.example.a45mph/vehicles.csv";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testProfileCreation() {
        try {
            assert InstrumentationTestHelper.setUpTests(FILEPATH);
            VehicleProfile vp = VehicleProfilesActivity.createVehicleProfile("This","That","This and That");

            assertEquals(vp.getMake(),"This");
            assertEquals(vp.getModel(), "That");
            assertEquals(vp.getName(),"This and That");
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testProfileStorage() {
        try {
            assert InstrumentationTestHelper.setUpTests(FILEPATH);
            VehicleProfile vp = VehicleProfilesActivity.createVehicleProfile("This","That","This and That");

            Scanner s = new Scanner(new File(FILEPATH));
            vp.transfer();
            assert InstrumentationTestHelper.testTransfer(vp,s);
            assert !s.hasNextLine();
        } catch (Exception e) {
            assert InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testReadLog() {
        VehicleProfile vp = new VehicleProfile("Test","Test","Test");
        vp.setEntry();

        Scanner lineScanner = new Scanner(vp.entry);
        lineScanner.useDelimiter(",");
        VehicleProfile test = VehicleProfile.readLog(lineScanner);
        test.setEntry();

        assertEquals(vp.entry,test.entry);
    }
}
