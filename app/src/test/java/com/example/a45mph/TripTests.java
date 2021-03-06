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
import java.time.Month;
import java.util.Objects;
import java.util.Scanner;

public class TripTests
{
    private boolean recreateTestFile(File file)
    {
        boolean deleted;
        boolean created;

        try {
            file.delete();
            created = file.createNewFile();
        } catch (Exception e) {
            // some kind of issue writing to file
            return false;
        }

        return created;
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
        VehicleProfile testCar = new VehicleProfile("Test","Test","Test");
        TripDataLog test = new TripDataLog(50, 2.5, thisInstant, testCar);
        assert (thisInstant == test.getTime());
    }

    @Test
    public void testTripLogTransfer() {
        LocalDateTime thisInstant = LocalDateTime.now();
        VehicleProfile testCar = new VehicleProfile("Test","Test","Test");
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant, testCar);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant, testCar);

        testTrip1.setEntry();
        testTrip2.setEntry();

        String testEntry = thisInstant.toString()+","+testCar.getName()+","+testTrip1.getConsumption()
               +","+ testTrip1.getOdometer()+","+testTrip1.getMileage()+ "\n";

        assert (Objects.equals(testTrip1.toString(), testEntry));

        testEntry = thisInstant.toString()+","+testCar.getName()+","+testTrip2.getConsumption()
                +","+ testTrip2.getOdometer()+","+testTrip2.getMileage()+"\n";

        assert (Objects.equals(testTrip2.toString(), testEntry));

    }

    @Test
    public void testGetDateTime() {
        LocalDateTime testTimeAM = LocalDateTime.of(2021, Month.NOVEMBER, 14, 7, 30, 30);
        LocalDateTime testTimePM = LocalDateTime.of(2020, Month.OCTOBER, 31, 15, 15, 45);
        assert (DataLog.getDateAsString(testTimeAM).equals("11-14-2021"));
        assert (DataLog.getTimeAsString(testTimeAM).equals("07:30:30 AM"));
        assert (DataLog.getDateAndTime(testTimeAM).equals("11-14-2021 07:30:30 AM"));
        assert (DataLog.getDateAsString(testTimePM).equals("10-31-2020"));
        assert (DataLog.getTimeAsString(testTimePM).equals("03:15:45 PM"));
        assert (DataLog.getDateAndTime(testTimePM).equals("10-31-2020 03:15:45 PM"));
    }

    @Test
    public void testWritingToFile() {
        LocalDateTime thisInstant = LocalDateTime.now();
        VehicleProfile testCar = new VehicleProfile("Test","Test","Test");
        TripDataLog testTrip1 = new TripDataLog(50, 2.5, thisInstant, testCar);
        TripDataLog testTrip2 = new TripDataLog(30, 6, thisInstant, testCar);

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
