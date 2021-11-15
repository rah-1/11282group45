package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button recordTripButton;
    private Button vehicleProfilesButton;
    private Button allCalculatorsButton;
    private Button allRecordsButton;
    private Button environmentalTrackingButton;
    private Button selectButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VehicleSelectionActivity.profileAdapter = new VehicleProfileAdapter(this);

        if (VehicleProfileAdapter.currentProfile != null)
            VehicleSelectionActivity.showCurrentProfile(this);
        else
            VehicleSelectionActivity.warnNoSelection(this,"No Profile Selected.");

        recordTripButton = (Button) findViewById(R.id.recordtripbutton);
        recordTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordTripActivity();
            }
        });

        vehicleProfilesButton = (Button) findViewById(R.id.vehicleprofilesbutton);
        vehicleProfilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleProfilesActivity();
            }
        });

        allCalculatorsButton = (Button) findViewById(R.id.allcalculatorsbutton);
        allCalculatorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllCalculatorsActivity();
            }
        });

        allRecordsButton = (Button) findViewById(R.id.allrecords);
        allRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allDataLogsActivity();
            }
        });

        environmentalTrackingButton = (Button) findViewById(R.id.environmentaltrackingbutton);
        environmentalTrackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                environmentalTrackingActivity();
            }
        });

        selectButton = (Button) findViewById(R.id.selectprofilebutton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVehicleProfile();
            }
        });
    }

    private void selectVehicleProfile() {
        startActivity(new Intent(this, VehicleSelectionActivity.class));
    }

    public void recordTripActivity() {
        Intent intent = new Intent(this, RecordTripActivity.class);
        startActivity(intent);
    }
    public void vehicleProfilesActivity() {
        Intent intent = new Intent(this, VehicleProfilesActivity.class);
        startActivity(intent);
    }
    public void openAllCalculatorsActivity() {
        Intent intent = new Intent(this, AllCalculatorsActivity.class);
        startActivity(intent);
    }
    public void allDataLogsActivity() {
        Intent intent = new Intent(this, AllDataLogsActivity.class);
        startActivity(intent);
    }
    public void environmentalTrackingActivity() {
        Intent intent = new Intent(this, EnvironmentalTrackingActivity.class);
        startActivity(intent);
    }

}