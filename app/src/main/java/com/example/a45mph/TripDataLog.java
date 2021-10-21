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

    private void setAll(double odom, double con)
    {
        odometer = odom;
        consumption = con;
        mileage = odom / con;
    }

    // defaults should not be necessary, but in the event that a default constructor call is made on accident,
    // we set all attributes to 0.
    public TripDataLog()
    {
        odometer = 0;
        consumption = 0;
        mileage = 0;
    }

    public TripDataLog(double odom, double con)
    {
        setAll(odom, con);
    }

    public TripDataLog(double odom, double con, LocalDateTime time)
    {
        super(time);
        setAll(odom,con);
    }

    public double getConsumption() { return consumption; }
    public double getOdometer() { return odometer; }
    public double getMileage() { return mileage; }
  
    public void transfer() throws IOException {
        File tripLogFile = new File("/data/data/com.example.a45mph/tripLog.csv");
        setEntry();
        Log.d("File Man", tripLogFile.getAbsoluteFile().toString());
        Log.d("File Man", entry);

        if(!tripLogFile.exists()) {
            if (!tripLogFile.createNewFile())
            {
                throw new IOException("Unable to Create " + tripLogFile.getAbsoluteFile().toString());
            }

            Log.d("File Man", "File created successfully");

            FileWriter fw = new FileWriter(tripLogFile);
            fw.write(entry);
            fw.close();
        }
        else
        {
            Log.d("File Man","File exists");
            FileWriter fw = new FileWriter(tripLogFile, true);
            fw.write(entry);
            fw.close();
        }

    }

    @Override
    public void setEntry()
    {
        entry = time + "," + consumption + "," + odometer + "," + mileage + "\n";
    }

}