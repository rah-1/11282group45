package com.example.a45mph;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class ProfileSelectionInstrumentTests {
    private final String FILEPATH = "/data/data/com.example.a45mph/vehicles.csv";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

    @Test
    public void testAditionToList()
    {
        try {
            InstrumentationTestHelper.setUpFile(FILEPATH);
            VehicleProfile vp = new VehicleProfile();
            vp.transfer();
            VehicleProfileAdapter adapter = new VehicleProfileAdapter();
            assertEquals(vp.getName(), adapter.getProfiles().get(0).getName());
            assertEquals(vp.getMake(), adapter.getProfiles().get(0).getMake());
            assertEquals(vp.getModel(), adapter.getProfiles().get(0).getModel());
            assertEquals(vp.getVehicleID(), adapter.getProfiles().get(0).getVehicleID());
            assertEquals(vp.getTime(),adapter.getProfiles().get(0).getTime());
        } catch (Exception e) {
            InstrumentationTestHelper.exceptionHandler(e);
        }
    }

    @Test
    public void testSelection()
    {
        try {
            InstrumentationTestHelper.setUpFile(FILEPATH);
            VehicleProfile vp = new VehicleProfile();
            vp.transfer();
            VehicleProfileAdapter adapter = new VehicleProfileAdapter();
            adapter.selectProfile(0);
            VehicleProfile curr = VehicleProfileAdapter.currentProfile;
            assertEquals(vp.getName(), curr.getName());
            assertEquals(vp.getMake(), curr.getMake());
            assertEquals(vp.getModel(), curr.getModel());
            assertEquals(vp.getVehicleID(), curr.getVehicleID());
            assertEquals(vp.getTime(), curr.getTime());
        } catch (Exception e) {
            InstrumentationTestHelper.exceptionHandler(e);
        }
    }
}
