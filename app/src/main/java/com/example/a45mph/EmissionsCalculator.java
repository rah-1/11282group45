package com.example.a45mph;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;
import java.util.Scanner;

public abstract class EmissionsCalculator {

    // a method for getting the emissions based of the distance of a single trip.
    public static double getTripEmissions(TripDataLog trip)
    {
        return trip.getOdometer() * trip.getVehicle().getCO2();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static double getTotalEmissions(Scanner s, VehicleProfile vp)
    {
        double sum = 0;

        while (s.hasNextLine())
        {
            TripDataLog trip = TripDataLog.readLog(DataLog.makeLineScanner(s));
            if (Objects.equals(vp.getName(),trip.getVehicle().getName()))
                sum += getTripEmissions(trip);
        }

        return sum;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static double getAverageEmissions(Scanner s, VehicleProfile vp)
    {
        double sum = 0;
        int i = 0;

        while (s.hasNextLine())
        {
            TripDataLog trip = TripDataLog.readLog(DataLog.makeLineScanner(s));
            if (Objects.equals(vp.getName(),trip.getVehicle().getName()))
            {
                sum += getTripEmissions(trip);
                i++;
            }
        }

        return sum / i;
    }
}