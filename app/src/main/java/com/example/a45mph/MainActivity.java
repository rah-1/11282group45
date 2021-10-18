package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button recordTripButton;
    private Button vehicleProfilesButton;
    private Button allCalculatorsButton;
    private Button allDataLogsButton;
    private Button allRecordsButton;
    private Button environmentalTrackingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        allDataLogsButton = (Button) findViewById(R.id.alldatalogs);
        allDataLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allDataLogsActivity();
            }
        });

        allRecordsButton = (Button) findViewById(R.id.allrecords);
        allRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allRecordsActivity();
            }
        });

        environmentalTrackingButton = (Button) findViewById(R.id.environmentaltrackingbutton);
        environmentalTrackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                environmentalTrackingActivity();
            }
        });
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
    public void allRecordsActivity() {
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivity(intent);
    }
    public void environmentalTrackingActivity() {
        Intent intent = new Intent(this, EnvironmentalTrackingActivity.class);
        startActivity(intent);
    }

    // TODO: add event handlers here
    // main activity events focus on jumping to the other activities.
    // should be deprecated now
    public void jumpToFuelCosts(View view) { }
    public void jumpToHypotheticalBuyableFuel(View view) { }
    public void jumpToTrip(View view) { }

}