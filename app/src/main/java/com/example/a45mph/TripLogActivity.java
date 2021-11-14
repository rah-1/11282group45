package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

public class TripLogActivity extends AppCompatActivity {
    private RecyclerView tripList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_log);

        tripList = findViewById(R.id.triplogrecyclerview);
        tripList.setLayoutManager(new LinearLayoutManager(this));
        tripList.setAdapter(new TripDataLogAdapter(this));

    }
}