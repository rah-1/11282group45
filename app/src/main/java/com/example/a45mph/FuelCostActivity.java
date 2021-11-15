package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class FuelCostActivity extends AppCompatActivity {
    private EditText unitCostField;
    private EditText amtBoughtField;
    private Button calculateButton;
    private CheckBox isHypotheticalCheck;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_cost);

        // Create onClick events
        calculateButton = (Button) findViewById(R.id.fuelcostcalculatebutton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCost(true);
            }
        });


        unitCostField = (EditText) findViewById(R.id.fuelcostunitpricefield);
        amtBoughtField = (EditText) findViewById(R.id.fuelcostfuelamountfield);
        isHypotheticalCheck = (CheckBox) findViewById(R.id.ishypotheticalcheck);
        resultText = (TextView) findViewById(R.id.fuelcostresulttext);
    }

    public static double calculateCost(double unitPrice, double amtBought, File file, boolean clear) throws IOException
    {
        double result = FuelCalculators.fuelCost(unitPrice,amtBought);

        if(clear) {
            FuelCalculators.transferLogs(file);
        }

        return result;
    }

    public static double calculateCost(double unitPrice, double amtBought, boolean clear) throws IOException
    {
        return calculateCost(unitPrice,amtBought,new File(ExpenditureDataLog.FILEPATH),clear);
    }

    public static double calculateHypotheticalCost(double unitPrice, double amtBought)
    {
        return FuelCalculators.hypotheticalFuelCost(unitPrice, amtBought);
    }

    public void calculateCost(boolean immediateTransfer)
    {
        boolean hypothetical = isHypotheticalCheck.isChecked();
        double result = -1; // this function returns -1 in the event of an error
        String errorMessage = "No Error";

        try {
            // obtain input data from text fields
            double unitCost = Double.parseDouble(unitCostField.getText().toString());
            double amtBought = Double.parseDouble(amtBoughtField.getText().toString());

            if (hypothetical)
            { result = calculateHypotheticalCost(unitCost,amtBought); }
            else
            { result = calculateCost(unitCost,amtBought, this.getFileStreamPath(ExpenditureDataLog.FILE),
                    immediateTransfer); }

        } catch (NumberFormatException e) {
            // write to the screen that there is an issue with the input
            errorMessage = "Error: Invalid Input!";
        } catch (ArithmeticException e) {
            // write to the screen somewhere that negative amounts for either field are disallowed
            errorMessage = "Error: Arithmetic Error!";
        } catch (IOException e) {
            // write to the screen somewhere that a file error has occurred
            errorMessage = "Error: File IO Error!";
        } catch (Exception e) {
            // write to the screen that something unexplained has happened
            errorMessage = "Error: Something Went Wrong";
        }

        String resultString = String.format("$%.2f",result);
        Log.d("calculateCost", errorMessage);
        Log.d("calculateCost", resultString);

        if (result != -1)
            resultText.setText(resultString);
        else
            resultText.setText(errorMessage);
    }

}