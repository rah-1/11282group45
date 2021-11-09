package com.example.a45mph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;

public class ExpendHolder extends RecyclerView.ViewHolder {
    private View view;
    private TextView exp;
    private TextView amt;
    private TextView time;
    private TextView vehicle;
    private ConstraintLayout layout;

    public ExpendHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        // TODO make resources for the consumptio, odometer, time and vehicle fields in the trip record list activity
        layout = view.findViewById(R.id.vehiclelistitem);
    }

    public void setExpenditure(double e) { exp.setText(Double.toString(e)); }
    public void setExpenditure(String e) { exp.setText(e); }

    public void setAmountBought(double a) { amt.setText(Double.toString(a));}
    public void setAmountBought(String a) { amt.setText(a); }

    public void setTime(LocalDateTime t) { time.setText(t.toString()); }
    public void setTime(String t) { time.setText(t); }

    public void setVehicle(VehicleProfile v) { vehicle.setText(v.getName()); }
    public void setVehicle(String v) { vehicle.setText(v); }

    public ConstraintLayout getLayout() { return layout; }

}
