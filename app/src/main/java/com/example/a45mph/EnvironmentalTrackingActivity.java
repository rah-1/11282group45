package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class EnvironmentalTrackingActivity extends AppCompatActivity {
    private RecyclerView emissionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_tracking);

        emissionList = findViewById(R.id.emissionlogrecycler);
        emissionList.setLayoutManager(new LinearLayoutManager(this));
        emissionList.setAdapter(new EmissionDataLogAdapter());

    }

    // devise methods for viewing environmental impact
}