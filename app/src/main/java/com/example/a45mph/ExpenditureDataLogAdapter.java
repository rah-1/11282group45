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

public class ExpenditureDataLogAdapter extends RecyclerView.Adapter {
    private ArrayList<ExpenditureDataLog> expenditures;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ExpenditureDataLogAdapter()
    {
        try {
            expenditures = ExpenditureDataLog.loadExpenditureDataLogs();
            Log.d("Expend Log Presentation", "Listing Successful");
        } catch (IOException e) {
            expenditures = new ArrayList<ExpenditureDataLog>();
            Log.d("ExpendLog Presentation", "Listing Unsuccessful");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileHolder(((View) LayoutInflater.from(parent.getContext()).inflate(0, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ExpendHolder expend = (ExpendHolder) holder;


    }

    @Override
    public int getItemCount() {
        return expenditures.size();
    }

    public ArrayList<ExpenditureDataLog> getExpenditures()
    {
        return expenditures;
    }
}
