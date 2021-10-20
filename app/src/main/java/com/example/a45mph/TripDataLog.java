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

    public TripDataLog()
    {
        setAll(0,0);
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
        Log.d("File Man", tripLogFile.getAbsoluteFile().toString());

        if(!tripLogFile.exists()){
            if (!tripLogFile.createNewFile())
            {
                throw new IOException("Unable to Create " + tripLogFile.getAbsoluteFile().toString());
            }

            Log.d("File Man", "File created successfully");

            FileWriter fw = new FileWriter(tripLogFile);
            fw.write(time + "," + consumption + "," + odometer + "," + mileage + "\n");
            fw.close();
        }
        else
        {
            Log.d("File Man","File exists");
            FileWriter fw = new FileWriter(tripLogFile);
            fw.write(time + "," + consumption + "," + odometer + "," + mileage + "\n");
            fw.close();
        }

}
}