package com.example.a45mph;

abstract public class FuelCalculators {
    public static double hypotheticalBuyableFuel(double unitPrice, double amtHeld) throws ArithmeticException
    {
        if (unitPrice <= 0 || amtHeld < 0) {
            throw new ArithmeticException();
        }

        return amtHeld / unitPrice;
    }
}
