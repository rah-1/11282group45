package com.example.a45mph;

import android.security.AppUriAuthenticationPolicy;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;

public class EmissionHolder extends RecyclerView.ViewHolder {

    private TextView gCO2;
    private TextView tripTime;
    private TextView vehicle;
    private ConstraintLayout layout;

    public EmissionHolder(@NonNull View itemView) {
        super(itemView);

        gCO2 = (TextView) itemView.findViewById(R.id.emissiontextviewemissionslistitem);
        tripTime = (TextView) itemView.findViewById(R.id.timetextviewemissionslistitem);
        vehicle = (TextView) itemView.findViewById(R.id.vehicletextviewemissionslistitem);
        layout = (ConstraintLayout) itemView.findViewById(R.id.emissionslistitem);
    }

    public void setVehicle(VehicleProfile vp) {
        vehicle.setText(vp.getName());
    }
    public void setVehicle(String vpName) {
        vehicle.setText(vpName);
    }

    public void setgCO2(double gCO2) {
        this.gCO2.setText(Double.toString(gCO2));
    }
    public void setgCO2(String gCO2) {
        this.gCO2.setText(gCO2);
    }

    public void setTripTime(LocalDateTime t) {
        tripTime.setText(t.toString());
    }
    public void setTripTime(String t) {
        tripTime.setText(t);
    }

    public ConstraintLayout getLayout() {
        return layout;
    }

}
