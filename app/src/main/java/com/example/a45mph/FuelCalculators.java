package com.example.a45mph;

import java.util.ArrayList;

abstract public class FuelCalculators {
    private static ArrayList<Double> fuelExpenditure = new ArrayList<>();
    private static ArrayList<Double> fuelBought = new ArrayList<>();

    // gettor for testing fuelCost method
    public static ArrayList<Double> getFuelBought()
    {
        return fuelBought;
    }

    public static ArrayList<Double> getFuelExpenditure()
    {
        return fuelExpenditure;
    }

    public static void transferLogs()
    {
        clearBought();
        clearExpenditure();
    }

    private static void clearExpenditure()
    {
        // TODO: transfer to logs when they exist
        fuelExpenditure.clear();
    }

    private static void clearBought()
    {
        // TODO: transfer to logs when they exist
        fuelBought.clear();
    }

    public static double hypotheticalBuyableFuel(double unitPrice, double amtHeld) throws ArithmeticException
    {
        if (unitPrice <= 0 || amtHeld < 0) {
            throw new ArithmeticException();
        }


        return amtHeld / unitPrice;
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
        double result = hypotheticalFuelCost(unitPrice,amtBought);
        fuelBought.add(amtBought);
        fuelExpenditure.add(result);

        return result;
    }



}
