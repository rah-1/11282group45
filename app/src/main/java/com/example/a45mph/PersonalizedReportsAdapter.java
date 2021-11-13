package com.example.a45mph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalizedReportsAdapter extends RecyclerView.Adapter<PersonalizedReportsAdapter.ReportsHolder>{
    Context reportsAdapterContext;
    public PersonalizedReportsAdapter (Context myContext) {
        reportsAdapterContext = myContext;
    }

    @NonNull
    @Override
    public ReportsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReportsHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsHolder holder, int position) {
        holder.toFillEndDate.setText(PersonalizedReportsActivity.allReports.allReports.get(position).getReportEndDate());
    }

    @Override
    public int getItemCount() {
        return PersonalizedReportsActivity.allReports.allReports.size();
    }

    public class ReportsHolder extends RecyclerView.ViewHolder {

        TextView toFillEndDate;

        public ReportsHolder (@NonNull View reportView) {
            super(reportView);
            toFillEndDate = reportView.findViewById(R.id.enddateindicator);
        }
    }
}
