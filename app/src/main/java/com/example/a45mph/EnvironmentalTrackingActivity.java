package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Scanner;

public class EnvironmentalTrackingActivity extends AppCompatActivity {
    private RecyclerView emissionList;
    private TextView avg;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_tracking);

        emissionList = (RecyclerView) findViewById(R.id.emissionlogrecycler);
        emissionList.setLayoutManager(new LinearLayoutManager(this));
        emissionList.setAdapter(new EmissionDataLogAdapter(this));

        avg = (TextView) findViewById(R.id.averagegCO2textviewemissionscalculator);
        total = (TextView) findViewById(R.id.gCO2textviewemissionscalculator);

        try {

            if(VehicleSelectionActivity.warnNoSelection(this))
                throw new IOException();

            avg.setText(Double.toString(EmissionsCalculator.getAverageEmissions(new Scanner(getApplicationContext().getFileStreamPath(TripDataLog.FILE)),
                    VehicleProfileAdapter.currentProfile)) + " grams of CO2");
            total.setText(Double.toString(EmissionsCalculator.getTotalEmissions(new Scanner(getApplicationContext().getFileStreamPath(TripDataLog.FILE)),
                    VehicleProfileAdapter.currentProfile)) + " grams of CO2");
        } catch (Exception e) {
            avg.setText("? grams of CO2");
            total.setText(avg.getText());
        }
    }

}