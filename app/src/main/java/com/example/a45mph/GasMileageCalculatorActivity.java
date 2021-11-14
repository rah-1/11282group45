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

import java.io.IOException;

public class GasMileageCalculatorActivity extends AppCompatActivity {
    private Button calculateButton;
    private EditText fuelUsedField;
    private EditText distanceDrivenField;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_mileage_calculator);

        calculateButton = (Button) findViewById(R.id.calculatempgbutton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                calculateMileage();
            }
        });
        distanceDrivenField = (EditText) findViewById(R.id.distancedrivenfield);
        fuelUsedField = (EditText) findViewById(R.id.fuelusedfield);
        resultText = (TextView) findViewById(R.id.mpgresulttext);
    }



    // devise methods of calculating gas mileage
    public static double calculateMileage(double distance, double amtUsed){
        return FuelCalculators.gasMileage(distance, amtUsed);
    }

    public double calculateMileage(){
        double result = -1;
        String errorMessage = "No Error";

        try {
            double distance = Double.parseDouble(distanceDrivenField.getText().toString());
            double amtUsed = Double.parseDouble(fuelUsedField.getText().toString());

            result = FuelCalculators.gasMileage(distance, amtUsed);
        }
        catch (NumberFormatException e) {
            // write to the screen that there is an issue with the input
            errorMessage = "Error: Invalid Input!";
        } catch (ArithmeticException e) {
            // write to the screen somewhere that negative amounts for either field are disallowed
            errorMessage = "Error: Arithmetic Error!";
        } catch (Exception e) {
            // write to the screen that something unexplained has happened
            errorMessage = "Error: Something Went Wrong";
        }

        String resultString = String.format("%.2f mpg" ,result);
        Log.d("calculateCost", errorMessage);
        Log.d("calculateCost", resultString);

        if (result != -1)
            resultText.setText(resultString);
        else
            resultText.setText(errorMessage);

        return result;
    }
}