package com.example.a45mph;

import java.io.BufferedWriter;
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
        File tempFile = new File("tripLog.csv");
        if(tempFile.exists() == false){
            BufferedWriter bf = new BufferedWriter((new FileWriter("tripLog.csv")));
            bf.write(time + "," + consumption + "," + odometer + "," + mileage);
            bf.close();
        }
        else {
            FileWriter fw = new FileWriter("tripLog.csv", true);
            fw.write("," + time + "," + consumption + "," + odometer + "," + mileage);
            fw.close();
        }
    }
}
