package com.example.a45mph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView make;
    private TextView model;
    private ConstraintLayout layout;

    public ProfileHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.nametextvehiclelist);
        make = itemView.findViewById(R.id.maketextvehiclelist);
        model = itemView.findViewById(R.id.modeltextvehiclelist);
        layout = itemView.findViewById(R.id.vehiclelistitem);
    }

    public void setName(String n)
    {
        name.setText(n);
    }

    public void setMake(String m)
    {
        make.setText(m);
    }

    public void setModel(String m)
    {
        model.setText(m);
    }

    public ConstraintLayout getLayout() { return layout; }
}
