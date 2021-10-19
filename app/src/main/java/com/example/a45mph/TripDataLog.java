package com.example.a45mph;

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
}
