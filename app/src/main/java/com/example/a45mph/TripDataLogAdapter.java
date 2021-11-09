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

public class TripDataLogAdapter extends RecyclerView.Adapter {
    private ArrayList<TripDataLog> tripLogs;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TripDataLogAdapter()
    {
        try {
            tripLogs = TripDataLog.loadTripDataLogs();
            Log.d("Trip Log Presentation", "Listing Successful");
        } catch (IOException e) {
            tripLogs = new ArrayList<TripDataLog>();
            Log.d("Trip Log Presentation", "Listing Unsuccessful");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO Make a resource for the items in list
        return new ProfileHolder(((View) LayoutInflater.from(parent.getContext()).inflate(0, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TripHolder trip = (TripHolder) holder;



    }

    @Override
    public int getItemCount() {
        return tripLogs.size();
    }

    public ArrayList<TripDataLog> getTrips()
    {
        return tripLogs;
    }
}
