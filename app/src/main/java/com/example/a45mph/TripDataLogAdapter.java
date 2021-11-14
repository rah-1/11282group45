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
        return new TripHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TripHolder trip = (TripHolder) holder;

        trip.setConsumption(tripLogs.get(position).getConsumption());
        trip.setOdometer(tripLogs.get(position).getOdometer());
        trip.setTime(tripLogs.get(position).getTime());
        trip.setVehicle(tripLogs.get(position).getVehicle());
    }

    @Override
    public int getItemCount() {
        return tripLogs.size();
    }

    public ArrayList<TripDataLog> getTrips()
    {
        return tripLogs;
    }
    public TripDataLog getTrip(int position) {
        return tripLogs.get(position);
    }
}
