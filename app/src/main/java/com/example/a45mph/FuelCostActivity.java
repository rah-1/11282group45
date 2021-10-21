package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.IOException;

public class FuelCostActivity extends AppCompatActivity {
    private EditText unitCostField;
    private EditText amtBoughtField;
    private Button calculateButton;
    private RadioButton isHypotheticalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_cost);

        // Create onClick events
        //calculateButton = (Button) findViewById(R.id.calculatebutton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                calculateCost(true);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static double calculateCost(double unitPrice, double amtBought, boolean clear) throws IOException
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateCost(boolean immediateTransfer)
    {
        boolean hypothetical = isHypotheticalButton.isActivated();
        double result = -1;

        try {
            // obtain input data from text fields
            double unitCost = Double.parseDouble(unitCostField.getText().toString());
            double amtBought = Double.parseDouble(amtBoughtField.getText().toString());

            if (hypothetical)
            { result = calculateHypotheticalCost(0.0,0.0); }
            else
            { result = calculateCost(0.0,0.0, immediateTransfer); }

        } catch (ArithmeticException e) {
            // write to the screen somewhere that negative amounts for either field are disallowed
        } catch (IOException e) {
            // write to the screen somewhere that another type of error has occurred
        } catch (Exception e) {

        }

        return result;
    }

}