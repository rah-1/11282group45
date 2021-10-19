package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecordTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_trip);
    }

    // devise method of letting the user record trip data, using the TripDataLogs class
    // This data should immediately be put into the user's data files
}