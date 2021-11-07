package com.example.a45mph;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class TripDataLog extends DataLog {
    private double odometer;
    private double consumption;
    private double mileage;
    private final String FILEPATH = "/data/data/com.example.a45mph/tripLog.csv";

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

}