package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class ViewPersonalizedReportActivity extends AppCompatActivity {
    private TextView startDate, endDate, perfRating, TFConsumption, TFCost, avgMileage, enviroImpact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personalized_report);

        startDate = (TextView) findViewById(R.id.dateone);
        endDate = (TextView) findViewById(R.id.datetwo);
        perfRating = (TextView) findViewById(R.id.starstextview);
        TFConsumption = (TextView) findViewById(R.id.totalfuelcosttextview);
        TFCost = (TextView) findViewById(R.id.totalfuelcosttextview);
        avgMileage = (TextView) findViewById(R.id.averagegasmileagetextview);
        enviroImpact = (TextView) findViewById(R.id.environmentalimpacttextview);


    }
    //TODO reassign TextView objects with proper values for the selected report
    // someTextView.setText(PR.get(attribute) + " units")

}