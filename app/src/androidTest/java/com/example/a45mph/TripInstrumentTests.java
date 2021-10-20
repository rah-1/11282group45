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

@RunWith(AndroidJUnit4.class)
public class TripInstrumentTests {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myapplication", appContext.getPackageName());
    }

    @Test
    public void testTripLogIO() {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant);

        try {
            File f = new File("/data/data/com.example.a45mph/tripLog.csv");
            if(f.delete())
            {
               Log.d("File Man", "File Existed");
            }

            testTrip1.transfer();
            testTrip2.transfer();

            Scanner s = new Scanner(f);

            assert (Objects.equals(testTrip1.toString(), s.nextLine() + "\n"));
            assert (Objects.equals(testTrip2.toString(), s.nextLine() + "\n"));
            assert (!s.hasNextLine());

        } catch (IOException e) {
            assert false;
        }
    }



}
