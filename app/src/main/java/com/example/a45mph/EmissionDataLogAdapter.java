package com.example.a45mph;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class EmissionDataLogAdapter extends RecyclerView.Adapter {
    private ArrayList<EmissionDataLog> emissions;

    public EmissionDataLogAdapter()
    {
        try {
            emissions = EmissionsCalculator.getAllEmissions(new Scanner(new File(TripDataLog.FILEPATH)));
        } catch (Exception e) {
            Log.d("Emission Presentation", "Listing Unsuccessful");
            emissions = new ArrayList<>();
        }
    }

    public EmissionDataLogAdapter(Context c)
    {
        try {
            emissions = EmissionsCalculator.getAllEmissions(new Scanner(c.getFileStreamPath("tripLog.csv")));
        } catch (Exception e) {
            Log.d("Emission Presentation", "Listing Unsuccessful");
            emissions = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmissionHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.emission_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmissionHolder emit = (EmissionHolder) holder;

        emit.setgCO2(emissions.get(position).getgCO2());
        emit.setVehicle(emissions.get(position).getVehicle());
        emit.setTripTime(emissions.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return emissions.size();
    }
}
