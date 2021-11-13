package com.example.a45mph;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalizedReportsAdapter extends RecyclerView.Adapter<PersonalizedReportsAdapter.ReportsHolder>{
    private Context reportsAdapterContext;
    public static PersonalizedReport currentReport = new PersonalizedReport();

    public PersonalizedReportsAdapter (Context myContext) {
        reportsAdapterContext = myContext;
    }

    @NonNull
    @Override
    public ReportsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReportsHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsHolder reportsHolder, int position) {
        reportsHolder.toFillEndDate.setText(PersonalizedReportsActivity.allReports.allReports.get(position).getReportEndDate());

        reportsHolder.getReportsLayout().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                currentReport = PersonalizedReportsActivity.allReports.allReports.get(reportsHolder.getAdapterPosition());
                Intent intent = new Intent(reportsAdapterContext, ViewPersonalizedReportActivity.class);
                reportsAdapterContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PersonalizedReportsActivity.allReports.allReports.size();
    }

    public class ReportsHolder extends RecyclerView.ViewHolder {

        private TextView toFillEndDate;
        private ConstraintLayout reportsLayout;

        public ReportsHolder (@NonNull View reportView) {
            super(reportView);
            toFillEndDate = reportView.findViewById(R.id.enddateindicator);
            reportsLayout = reportView.findViewById(R.id.reportlistitem);
        }

        public ConstraintLayout getReportsLayout() {return reportsLayout;}
    }
}
