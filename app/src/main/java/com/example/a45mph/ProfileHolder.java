package com.example.a45mph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileHolder extends RecyclerView.ViewHolder {
    private View view;
    private TextView name;
    private TextView make;
    private TextView model;
    private ConstraintLayout layout;

    public ProfileHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        name = view.findViewById(R.id.nametextvehiclelist);
        make = view.findViewById(R.id.maketextvehiclelist);
        model = view.findViewById(R.id.modeltextvehiclelist);
        layout = view.findViewById(R.id.vehiclelistitem);
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
