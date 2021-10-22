package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HypotheticalBuyableFuelActivity extends AppCompatActivity {
    private EditText unitPriceField;
    private EditText amtHeldField;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hypothetical_buyable_fuel);

        calculateButton = (Button) findViewById(R.id.hypotheticalcalculatebutton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               calculateHypotheticalBuyableFuel();
            }
        });

        unitPriceField = (EditText) findViewById(R.id.hypotheticalunitpricefield);
        amtHeldField = (EditText) findViewById(R.id.hypotheticaldollaramountfield);
        resultText = (TextView) findViewById(R.id.hypothticalresulttext);

    }

    public static double calculateHypotheticalBuyableFuel(double unitPrice, double amtHeld)
    {
        double result = -1;

        try {
            result = FuelCalculators.hypotheticalBuyableFuel(unitPrice,amtHeld);
            Log.d("Hypothetical Fuel", Double.toString(result));
        } catch (ArithmeticException e) {
            Log.d("Hypothetical Fuel", "Invalid Input");
        } catch (Exception f) {
            Log.d("Hypothetical Fuel", "Something Went Wrong");
        }

        return result;
    }

    public double calculateHypotheticalBuyableFuel()
    {
        double result = -1;
        String errorMessage = "No Error";

        // get data input from the text fields
        double unitPrice = Double.parseDouble(unitPriceField.getText().toString());
        double amtHeld = Double.parseDouble(amtHeldField.getText().toString());

        try {
            result = FuelCalculators.hypotheticalBuyableFuel(unitPrice,amtHeld);
        } catch (ArithmeticException e) {
            errorMessage = "Error: Non Positive Input Detected!";
        } catch (Exception f) {
            errorMessage = "Error: Something Went Wrong!";
        }

        Log.d("Hypothetical Fuel", errorMessage);
        Log.d("Hypothetical Fuel", Double.toString(result));

        if (result != -1)
            resultText.setText(Double.toString(result));
        else
            resultText.setText(errorMessage);

        return result;
    }
}