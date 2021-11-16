package com.example.a45mph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;

public class TripHolder extends RecyclerView.ViewHolder {

    private TextView odom;
    private TextView con;
    private TextView time;
    private TextView vehicle;
    private TextView mileage;
    private ConstraintLayout layout;

    public TripHolder(@NonNull View itemView) {
        super(itemView);

        odom = itemView.findViewById(R.id.odomtexttriplist);
        con = itemView.findViewById(R.id.contexttriplist);
        time = itemView.findViewById(R.id.timetexttriplist);
        vehicle = itemView.findViewById(R.id.vehicletexttriplist);
        mileage = itemView.findViewById(R.id.mileagetexttriplist);
        layout = itemView.findViewById(R.id.triplistitem);
    }

    public void setOdometer(double o) { odom.setText(Double.toString(o)); }
    public void setOdometer(String o) { odom.setText(o); }

    public void setConsumption(double c) { con.setText(Double.toString(c));}
    public void setConsumption(String c) { con.setText(c); }

    public void setTime(LocalDateTime t) { time.setText(DataLog.getDateAndTime(t)); }
    public void setTime(String t) { time.setText(t); }

    public void setVehicle(VehicleProfile v) { vehicle.setText(v.getName()); }
    public void setVehicle(String v) { vehicle.setText(v); }

    public void setMileage(double m) { mileage.setText(Double.toString(m)); }
    public void setMileage(String m) { mileage.setText(m); }

    public ConstraintLayout getLayout() { return layout; }
}
