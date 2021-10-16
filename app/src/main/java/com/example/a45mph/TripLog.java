package com.example.a45mph;

import java.time.LocalDateTime;

public class TripLog extends Log {
    private double odometer;
    private double consumption;
    private double mileage;

    private void setAll(double odom, double con)
    {
        odometer = odom;
        consumption = con;
        mileage = odom / con;
    }

    public TripLog()
    {
        setAll(0,0);
    }

    public TripLog(double odom, double con)
    {
        setAll(odom, con);
    }

    public TripLog(double odom, double con, LocalDateTime time)
    {
        super(time);
        setAll(odom,con);
    }

    public double getConsumption() { return consumption; }
    public double getOdometer() { return odometer; }
    public double getMileage() { return mileage; }
}
