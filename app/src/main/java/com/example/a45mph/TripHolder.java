package com.example.a45mph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;

public class TripHolder extends RecyclerView.ViewHolder {
    private View view;
    private TextView odom;
    private TextView con;
    private TextView time;
    private TextView vehicle;
    private ConstraintLayout layout;

    public TripHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        odom = view.findViewById(R.id.odomtexttriplist);
        con = view.findViewById(R.id.contexttriplist);
        time = view.findViewById(R.id.timetexttriplist);
        vehicle = view.findViewById(R.id.vehicletexttriplist);
        layout = view.findViewById(R.id.triplistitem);
    }

    public void setOdometer(double o) { odom.setText(Double.toString(o)); }
    public void setOdometer(String o) { odom.setText(o); }

    public void setConsumption(double c) { con.setText(Double.toString(c));}
    public void setConsumption(String c) { con.setText(c); }

    public void setTime(LocalDateTime t) { time.setText(t.toString()); }
    public void setTime(String t) { time.setText(t); }

    public void setVehicle(VehicleProfile v) { vehicle.setText(v.getName()); }
    public void setVehicle(String v) { vehicle.setText(v); }

    public ConstraintLayout getLayout() { return layout; }
}
