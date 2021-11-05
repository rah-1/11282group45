package com.example.a45mph;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class VehicleProfileAdapter extends RecyclerView.Adapter {
    private ArrayList<VehicleProfile> profiles;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public VehicleProfileAdapter()
    {
        try {
            profiles = VehicleProfile.loadVehicleProiles();
            Log.d("Profile Selection", "Listing Successful");
        } catch (IOException e) {
            profiles = new ArrayList<VehicleProfile>();
            Log.d("Profile Selection", "Listing Unsuccessful");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProfileHolder profileHolder = (ProfileHolder) holder;
        profileHolder.setName(profiles.get(position).getName());
        profileHolder.setMake(profiles.get(position).getMake());
        profileHolder.setModel(profiles.get(position).getModel());

        profileHolder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectProfile(profileHolder.getAdapterPosition());
            }
        });
    }

    public static void selectProfile(int position) {
        Log.d("Test", "Called successfully");
    }

    public ArrayList<VehicleProfile> getProfiles() {
        return null;
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
