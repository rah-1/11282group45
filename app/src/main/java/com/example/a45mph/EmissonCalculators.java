package com.example.a45mph;

public abstract class EmissonCalculators {
    public static double emissionsByGallon(double distanceTraveled, double gpm){
        double result;
        result = distanceTraveled * gpm;
        return result;
    }


}
