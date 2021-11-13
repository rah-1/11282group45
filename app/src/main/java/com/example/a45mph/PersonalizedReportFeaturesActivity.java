package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PersonalizedReportFeaturesActivity extends AppCompatActivity {

    private CheckBox totalFuelConsumption, totalFuelCost, averageGasMileage, environmentalImpact;
    private Button confirmFeaturesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_report_features);

        totalFuelConsumption = (CheckBox) findViewById(R.id.totalfuelconsumptioncheckbox);
        totalFuelCost = (CheckBox) findViewById(R.id.totalfuelcostcheckbox);
        averageGasMileage = (CheckBox) findViewById(R.id.averagegasmileagecheckbox);
        environmentalImpact = (CheckBox) findViewById(R.id.environmentalimpactcheckbox);

        confirmFeaturesButton = (Button) findViewById(R.id.confirmfeaturesbutton);
        confirmFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmReportFeatures();
            }
        });
    }

    // TODO: also adjust which features are shown in recyclerview
    public void confirmReportFeatures() {
        if (totalFuelConsumption.isChecked()) {PersonalizedReportsActivity.allReports.setEnableTotalFuelConsumption(true);}
        else {PersonalizedReportsActivity.allReports.setEnableTotalFuelConsumption(false);}
        if (totalFuelCost.isChecked()) {PersonalizedReportsActivity.allReports.setEnableTotalFuelCost(true);}
        else {PersonalizedReportsActivity.allReports.setEnableTotalFuelCost(false);}
        if (averageGasMileage.isChecked()) {PersonalizedReportsActivity.allReports.setEnableAverageGasMileage(true);}
        else {PersonalizedReportsActivity.allReports.setEnableAverageGasMileage(false);}
        if (environmentalImpact.isChecked()) {PersonalizedReportsActivity.allReports.setEnableEnvironmentalImpact(true);}
        else {PersonalizedReportsActivity.allReports.setEnableEnvironmentalImpact(false);}

        Intent intent = new Intent(this, PersonalizedReportsActivity.class);
        startActivity(intent);
    }
}