package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class PersonalizedReportsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button customizeFeaturesButton;
    private Switch enablePersonalizedReportsSwitch;
    private Spinner timePeriodDropdown;
    private static final String[] timePeriodOptions = {"Daily", "Weekly", "Monthly", "Annually"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_reports);

    timePeriodDropdown = (Spinner)findViewById(R.id.timeperioddropdown);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(PersonalizedReportsActivity.this,
        android.R.layout.simple_spinner_item,timePeriodOptions);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    timePeriodDropdown.setAdapter(adapter);
    timePeriodDropdown.setOnItemSelectedListener(this);

    enablePersonalizedReportsSwitch = (Switch) findViewById(R.id.reportsonoffswitch);
    enablePersonalizedReportsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
            {
                // do something when reports are ON
            }
            else
            {
                //do something when reports are OFF
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        Log.v("Time Period", (String) parent.getItemAtPosition(position));
        // store time period selection
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // do nothing -- leave this here
    }



    public void personalizedReportsFeaturesActivity() {
        Intent intent = new Intent(this, PersonalizedReportFeaturesActivity.class);
        startActivity(intent);
    }
}