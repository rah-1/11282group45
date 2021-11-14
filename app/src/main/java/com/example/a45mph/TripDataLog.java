package com.example.a45mph;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TripDataLog extends DataLog {
    private double odometer;
    private double consumption;
    private double mileage;
    public static final String FILEPATH = "/data/data/com.example.a45mph/files/tripLog.csv";
    public static final String FILE = "tripLog.csv";

    private void setAll(double odom, double con)
    {
        odometer = odom;
        consumption = con;
        try {
            mileage = Math.round((odom / con) * 100) / 100.00;
        } catch (ArithmeticException e) {
            mileage = 0;
        }
    }

    // defaults should not be necessary, but in the event that a default constructor call is made on accident,
    // we set all attributes to 0.
    public TripDataLog() {
        odometer = 0;
        consumption = 0;
        mileage = 0;
    }

    public TripDataLog(double odom, double con)
    {
        setAll(odom, con);
    }

    public TripDataLog(double odom, double con, LocalDateTime time) {
        super(time);
        setAll(odom,con);
    }

    public TripDataLog(double odom, double con, LocalDateTime time, VehicleProfile vehicle) {
        super(time, vehicle);
        setAll(odom,con);
    }

    public double getConsumption() { return consumption; }
    public double getOdometer() { return odometer; }
    public double getMileage() { return mileage; }
  
    public void transfer() throws IOException {
        File tripLogFile = new File(FILEPATH);
        setEntry();
        Log.d("File Man", tripLogFile.getAbsoluteFile().toString());
        Log.d("File Man", entry);

        transfer(tripLogFile);
    }

    @Override
    public void setEntry() {
        entry = time + "," + vehicle.getName() + "," + consumption + "," + odometer + "," + mileage + "\n";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static TripDataLog readLog(Scanner lineScanner)
    {
        LocalDateTime timestamp = LocalDateTime.parse(lineScanner.next());
        VehicleProfile vehicleProfile = VehicleSelectionActivity.profileAdapter.searchProfiles(lineScanner.next());
        double consumption = Double.parseDouble(lineScanner.next());
        double odometer = Double.parseDouble(lineScanner.next());

        return new TripDataLog(odometer,consumption,timestamp,vehicleProfile);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<TripDataLog> loadTripDataLogs() throws IOException
    {
        return loadTripDataLogs(new File(FILEPATH));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<TripDataLog> loadTripDataLogs(File file) throws IOException
    {
        ArrayList<TripDataLog> trips = new ArrayList<>();
        Scanner s = new Scanner(new File(FILEPATH));
        // iterate through the file and read the vehicle profiles
        try {
            while (s.hasNextLine())
            {
                // set up Scanner with comma delimiter and
                // read out all the attributes of the profile
                TripDataLog trip = readLog(makeLineScanner(s));
                trips.add(trip);
            }

            return trips;

        } catch (Exception e) {
            Log.d("Loading Trips","IOException Thrown");
            throw new IOException("Error Reading Trips");
        }
    }
}