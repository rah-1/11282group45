package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FuelCostActivity extends AppCompatActivity {
    private EditText unitCostField;
    private EditText amtBoughtField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_cost);

        // Create onClick events

    }

    public static double calculateCost(double unitPrice, double amtBought, boolean clear)
    {
        double result = FuelCalculators.fuelCost(unitPrice,amtBought);

        if(clear) {
            FuelCalculators.transferLogs();
        }

        return result;
    }

    public static double calculateHypotheticalCost(double unitPrice, double amtBought)
    {
        return FuelCalculators.hypotheticalFuelCost(unitPrice, amtBought);
    }

    public double calculateCost()
    {
        boolean hypothetical = true;
        double result = -1;

        // assess whether the data should be saved and then do the correct function
        // obtain input data from text fields


        try {
            if (hypothetical)
            { result = calculateHypotheticalCost(0.0,0.0); }
            else
            { result = calculateCost(0.0,0.0,true); }
        } catch (ArithmeticException e) {
            // write to the screen somewhere that negative amounts for either field are disallowed
        } catch (Exception f) {
            // write to the screen somewhere that another type of error has occurred
        }

        return result;
    }

}