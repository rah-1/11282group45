package com.example.a45mph;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileHolder extends RecyclerView.ViewHolder {
    private View view;
    private TextView name;
    private TextView make;
    private TextView model;

    public ProfileHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        name = view.findViewById(R.id.nametextvehiclelist);
        make = view.findViewById(R.id.maketextvehiclelist);
        model = view.findViewById(R.id.modeltextvehiclelist);
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
}
