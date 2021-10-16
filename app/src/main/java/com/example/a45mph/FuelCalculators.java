package com.example.a45mph;

abstract public class FuelCalculators {
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

    // Dummy method so that the project compiles while we work on other features. Remove when real
    // implementation exists
    public static double fuelCost(double unitPrice, double amtBought)
    {
        return 0;
    }



}
