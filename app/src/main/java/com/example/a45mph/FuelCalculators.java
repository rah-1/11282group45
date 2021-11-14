package com.example.a45mph;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

abstract public class FuelCalculators {
    private static ArrayList<ExpenditureDataLog> fuelExpenditure = new ArrayList<>();

    public static ArrayList<ExpenditureDataLog> getFuelExpenditure()
    {
        return fuelExpenditure;
    }

    public static void transferLogs(File file) throws IOException
    {
        for (ExpenditureDataLog ex : fuelExpenditure)
        {
            ex.transfer(file);
        }

        fuelExpenditure.clear();
    }

    public static void clearExpenditure()
    {
        fuelExpenditure.clear();
    }

    public static double hypotheticalBuyableFuel(double unitPrice, double amtHeld) throws ArithmeticException
    {
        if (unitPrice <= 0 || amtHeld < 0) {
            throw new ArithmeticException();
        }

        return Math.round((amtHeld / unitPrice) * 100) / 100.00;
    }

    public static double hypotheticalFuelCost(double unitPrice, double amtBought) throws ArithmeticException
    {
        if (unitPrice < 0 || amtBought < 0) {
            throw new ArithmeticException();
        }

        return Math.round(unitPrice * amtBought * 100) / 100.00;
    }

    public static double fuelCost(double unitPrice, double amtBought)
    {
        return fuelCost(unitPrice,amtBought,LocalDateTime.now(),VehicleProfileAdapter.currentProfile);
    }

    public static double fuelCost(double unitPrice, double amtBought, LocalDateTime time, VehicleProfile vehicle)
    {
        double result = hypotheticalFuelCost(unitPrice,amtBought);
        fuelExpenditure.add(new ExpenditureDataLog(result, amtBought, time, vehicle));

        return result;
    }

    public static double gasMileage(double distance, double amtUsed) {
        if(amtUsed <= 0 || distance <= 0){
            throw new ArithmeticException();
        }

        return Math.round(distance / amtUsed * 100) / 100.00;
    }



}
