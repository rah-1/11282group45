package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.time.LocalDateTime;

public class RecordTripActivity extends AppCompatActivity {
    private EditText odometerField;
    private EditText consumptionField;
    private Button recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_trip);

        recordButton = (Button) findViewById(R.id.recordtriprecordbutton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                recordTrip();
            }
        });

        odometerField = (EditText) findViewById(R.id.recordtripodometerfield);
        consumptionField = (EditText) findViewById(R.id.recordtripconsumptionfield);
    }

    // devise method of letting the user record trip data, using the TripDataLogs class
    // This data should immediately be put into the user's data files
    public static double recordTrip(double odom, double con) throws IOException
    {
        if (odom <= 0 || con <= 0)
            throw new ArithmeticException("Odometer or Consumption is 0");

        TripDataLog trip = new TripDataLog(odom,con);
        trip.transfer();
        return trip.getMileage();
    }

    // overloading this method for testing
    public static double recordTrip(double odom, double con, LocalDateTime time) throws IOException
    {
        if (odom <= 0 || con <= 0)
            throw new ArithmeticException("Odometer or Consumption is 0");

        TripDataLog trip = new TripDataLog(odom,con,time);
        trip.transfer();
        return trip.getMileage();
    }

    public double recordTrip()
    {
        String errorMessage = "No Error";
        boolean noExceptions = false;
        double mileage = -1;

        try {
            // get input from the fields
            double unitCost = Double.parseDouble(odometerField.getText().toString());
            double consumption = Double.parseDouble(consumptionField.getText().toString());
            mileage = recordTrip(unitCost,consumption);

            Toast.makeText(getApplicationContext(),"Trip Recorded with" +
                    " average gas mileage of " + mileage + ".",Toast.LENGTH_LONG).show();
            noExceptions = true;
        } catch (NumberFormatException e) {
            // write to the screen that there is an issue with the input
            errorMessage = "Error: Invalid Input!";
        } catch (ArithmeticException e) {
            // write to the screen somewhere that nonpositive amounts for either field are disallowed
            errorMessage = "Error: Arithmetic Error!";
        } catch (IOException e) {
            // write to the screen somewhere that a file error has occurred
            errorMessage = "Error: File IO Error!";
        } catch (Exception e) {
            // write to the screen that something unexplained has happened
            errorMessage = "Error: Something Went Wrong";
        }

        Log.d("Trip Record", errorMessage);
        Log.d("Trip Record", Double.toString(mileage));
        if (!noExceptions)
        {
            Toast.makeText(getApplicationContext(), "Error: " + errorMessage,Toast.LENGTH_LONG).show();
        }

        return mileage;
    }

}