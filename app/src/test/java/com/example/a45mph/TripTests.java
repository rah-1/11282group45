package com.example.a45mph;

import android.util.Log;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class TripTests
{
    private boolean recreateTestFile(File file)
    {
        boolean deleted;
        boolean created;

        try {
            deleted = file.delete();
            created = file.createNewFile();
        } catch (Exception e) {
            // some kind of issue writing to file
            return false;
        }

        return deleted && created;
    }


    @Test
    public void testConsumption()
    {
        TripDataLog test = new TripDataLog(50, 2.5);
        assert (test.getConsumption() == 2.5);
    }

    @Test
    public void testOdometer()
    {
        TripDataLog test = new TripDataLog(50, 2.5);
        assert (test.getOdometer() == 50);
    }

    @Test
    public void testMileageCalculation()
    {
        TripDataLog test = new TripDataLog(50, 2.5);
        assert (test.getMileage() == (50 / 2.5));
    }

    @Test
    public void testTripLogTiming()
    {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripDataLog test = new TripDataLog(50, 2.5, thisInstant);
        assert (thisInstant == test.getTime());
    }

    @Test
    public void testTripLogTransfer() {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant);

        testTrip1.setEntry();
        testTrip2.setEntry();

        String testEntry = thisInstant.toString()+","+testTrip1.getConsumption()
               +","+ testTrip1.getOdometer()+","+testTrip1.getMileage()+"\n";

        assert (Objects.equals(testTrip1.toString(), testEntry));

        testEntry = thisInstant.toString()+","+testTrip2.getConsumption()
                +","+ testTrip2.getOdometer()+","+testTrip2.getMileage()+"\n";

        assert (Objects.equals(testTrip2.toString(), testEntry));

    }

    @Test
    public void testWritingToFile() {
        LocalDateTime thisInstant = LocalDateTime.now();
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant);

        testTrip1.setEntry();
        testTrip2.setEntry();

        File file = new File("testlog.csv");
        assert recreateTestFile(file);

        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(testTrip1.toString());
            fw.write(testTrip2.toString());
            fw.close();

            Scanner s = new Scanner(file);
            assert (Objects.equals(testTrip1.toString(),s.nextLine() + "\n"));
            assert (Objects.equals(testTrip2.toString(),s.nextLine() + "\n"));
            s.close();
        } catch (Exception e) {
            // something went wrong
            assert false;
        }

    }
}
