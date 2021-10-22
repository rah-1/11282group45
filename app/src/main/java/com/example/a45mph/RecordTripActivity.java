package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.time.LocalDateTime;

public class RecordTripActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_trip);
    }

    // devise method of letting the user record trip data, using the TripDataLogs class
    // This data should immediately be put into the user's data files
    public static double recordTrip(double odom, double con) throws IOException
    {
        TripDataLog trip = new TripDataLog(odom,con);

        if (odom > 0 && con > 0)
            trip.transfer();
        else
            return 0;

        return trip.getMileage();
    }

    // overloading this method for testing
    public static double recordTrip(double odom, double con, LocalDateTime time) throws IOException
    {
        TripDataLog trip = new TripDataLog(odom,con,time);
        trip.transfer();

        if (odom > 0 && con > 0)
            trip.transfer();
        else
            return 0;

        return trip.getMileage();
    }




}