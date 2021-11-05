package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class VehicleSelectionActivity extends AppCompatActivity {
    private RecyclerView profileList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile_sel);

        Log.d("Profile Selection", "Begin View Init");
        profileList = findViewById(R.id.profileselectionview);
        profileList.setLayoutManager(new LinearLayoutManager(this));
        profileList.setAdapter(new VehicleProfileAdapter());
        Log.d("Profile Selection", "Recycler Creation Successful");

    }
}