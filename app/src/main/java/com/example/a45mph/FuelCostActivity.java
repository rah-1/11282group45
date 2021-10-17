package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FuelCostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_cost);
    }

    public double calculateCost(double unitPrice, double amtBought, boolean clear)
    {
        double result = FuelCalculators.fuelCost(unitPrice,amtBought);

        if(clear)
        {
            FuelCalculators.transferLogs();
        }

        return result;
    }

    public double calculateCost(View v)
    {
        // assess whether the data should be saved and then do the correct function
        return 0;
    }

}