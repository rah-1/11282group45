package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.IOException;

public class PersonalizedReportsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static ReportsContainer allReports;

    private Button customizeFeaturesButton;
    private Switch enablePersonalizedReportsSwitch;
    private Spinner timePeriodDropdown;
    private static final String[] timePeriodOptions = {"Daily", "Weekly", "Monthly", "Annually"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_reports);

    allReports = new ReportsContainer();

    timePeriodDropdown = (Spinner)findViewById(R.id.timeperioddropdown);
    ArrayAdapter<String> adapterDropdown = new ArrayAdapter<String>(PersonalizedReportsActivity.this,
        android.R.layout.simple_spinner_item,timePeriodOptions);

        adapterDropdown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    timePeriodDropdown.setAdapter(adapterDropdown);
    timePeriodDropdown.setOnItemSelectedListener(this);

    enablePersonalizedReportsSwitch = (Switch) findViewById(R.id.reportsonoffswitch);
    enablePersonalizedReportsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
            try {
                allReports.setReportsOn(isChecked);
            } catch (IOException e) {
                Log.e("Error", "Caught IOException.");
            }
        }

    });

    customizeFeaturesButton = (Button) findViewById(R.id.reportfeaturesbutton);
    customizeFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalizedReportsFeaturesActivity();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        Log.v("Time Period", (String) parent.getItemAtPosition(position));
        // store time period selection
        try {
            allReports.setTimePeriod((String) parent.getItemAtPosition(position));
        } catch (IOException e) {
            Log.e("Error", "Caught IOException.");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // do nothing -- leave this here
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setReportsOnOff(boolean isChecked) throws IOException {
        allReports.setReportsOn(isChecked);
    }



    public void personalizedReportsFeaturesActivity() {
        Intent intent = new Intent(this, PersonalizedReportFeaturesActivity.class);
        startActivity(intent);
    }
}