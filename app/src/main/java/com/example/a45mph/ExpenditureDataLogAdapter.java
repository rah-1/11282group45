package com.example.a45mph;

import android.content.Context;
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

    public ExpenditureDataLogAdapter(Context c)
    {
        try {
            expenditures = ExpenditureDataLog.loadExpenditureDataLogs(c.getFileStreamPath(ExpenditureDataLog.FILE));
            Log.d("Expend Log Presentation", "Listing Successful");
        } catch (IOException e) {
            expenditures = new ArrayList<ExpenditureDataLog>();
            Log.d("ExpendLog Presentation", "Listing Unsuccessful");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpendHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.expenditure_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ExpendHolder expend = (ExpendHolder) holder;
        expend.setExpenditure(expenditures.get(position).getExpenditure());
        expend.setAmountBought(expenditures.get(position).getAmountBought());
        expend.setTime(expenditures.get(position).getTime());
        expend.setVehicle(expenditures.get(position).getVehicle());


        expend.getLayout().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return expenditures.size();
    }

    public ArrayList<ExpenditureDataLog> getExpenditures()
    {
        return expenditures;
    }

    public ExpenditureDataLog getExpenditure(int position)
    {
        return expenditures.get(position);
    }
}
