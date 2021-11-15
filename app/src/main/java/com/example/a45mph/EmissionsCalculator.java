package com.example.a45mph;

import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class EmissionsCalculator {

    public static double getTripEmissions(TripDataLog trip)
    {
        double result = trip.getOdometer() * trip.getVehicle().getCO2();
        return Math.round(result * 100) / 100.0;
    }

    public static double getTotalEmissions(Scanner s, VehicleProfile vp)
    {
        double sum = 0;

        while (s.hasNextLine())
        {
            TripDataLog trip = TripDataLog.readLog(DataLog.makeLineScanner(s));
            if (Objects.equals(vp.getName(),trip.getVehicle().getName()))
                sum += getTripEmissions(trip);
        }

        return Math.round(sum * 100) / 100.0;
    }

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

        if (sum == 0 || i == 0)
            return 0;

        return Math.round((sum / i) * 100) / 100.0;
    }

    public static ArrayList<EmissionDataLog> getAllEmissions(File file) throws IOException {
        ArrayList<EmissionDataLog> emit = new ArrayList<>();

        ArrayList<TripDataLog> trips = TripDataLog.loadTripDataLogs(file);
        for (TripDataLog t : trips)
        {
            emit.add(new EmissionDataLog(getTripEmissions(t),t.getTime(),t.getVehicle()));
        }

        return emit;
    }
}