package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class HypotheticalBuyableFuelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hypothetical_buyable_fuel);
    }

    public double calculateHypotheticalBuyableFuel(View V)
    {
        double result = -1;

        try {
            result = FuelCalculators.hypotheticalBuyableFuel(0.0,0.0);
        } catch (ArithmeticException e) {
            // negative inputs not allowed here
        } catch (Exception f) {
            // something else went wrong
        }

        return result;
    }
}