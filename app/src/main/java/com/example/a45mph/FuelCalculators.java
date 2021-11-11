package com.example.a45mph;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

abstract public class FuelCalculators {
    private static ArrayList<ExpenditureDataLog> fuelExpenditure = new ArrayList<>();

    public static ArrayList<ExpenditureDataLog> getFuelExpenditure()
    {
        return fuelExpenditure;
    }

    public static void transferLogs() throws IOException
    {
        for (ExpenditureDataLog ex : fuelExpenditure)
        {
            ex.transfer();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static double fuelCost(double unitPrice, double amtBought)
    {
        double result = hypotheticalFuelCost(unitPrice,amtBought);
        fuelExpenditure.add(new ExpenditureDataLog(result, amtBought, LocalDateTime.now(), VehicleProfileAdapter.currentProfile));

        return result;
    }

    public static double gasMileage(double distance, double amtUsed ){
        if(amtUsed <= 0 || distance <= 0){
            throw new ArithmeticException();
        }

        return Math.round(distance / amtUsed * 100) / 100.00;
    }



}
