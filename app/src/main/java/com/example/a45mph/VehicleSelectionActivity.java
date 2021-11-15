package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class VehicleSelectionActivity extends AppCompatActivity {
    private RecyclerView profileList;
    public static VehicleProfileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile_sel);

        Log.d("Profile Selection", "Begin View Init");
        profileList = findViewById(R.id.profileselectionview);
        profileList.setLayoutManager(new LinearLayoutManager(this));
        profileList.setAdapter(profileAdapter);
        Log.d("Profile Selection", "Recycler Creation Successful");

        if (!warnNoSelection(this, "No Profile Selected."))
            showCurrentProfile(this);
    }

    public static boolean warnNoSelection(Context c)
    {
        return warnNoSelection(c, "No Profile Selected! Calculation Impossible!");
    }

    public static boolean warnNoSelection(Context c, String mes)
    {
        if (VehicleProfileAdapter.currentProfile != null)
            return false;

        Toast t = Toast.makeText(c,mes,Toast.LENGTH_LONG);
        t.show();
        return true;
    }

    public static void showCurrentProfile(Context c)
    {
        Toast t = Toast.makeText(c,"Current Profile is " + VehicleProfileAdapter.currentProfile.getName(),Toast.LENGTH_LONG);
        t.show();
    }
}