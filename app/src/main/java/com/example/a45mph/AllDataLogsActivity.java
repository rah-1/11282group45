package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllDataLogsActivity extends AppCompatActivity {
    private Button tripLogsButton;
    private Button expenditureLogsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data_logs);

        tripLogsButton = (Button) findViewById(R.id.triplogsbutton);
        tripLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTripLogsActivity();
            }
        });

        expenditureLogsButton = (Button) findViewById(R.id.fuelexpenditurelogsbutton);
        expenditureLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpenditureLogsActivity();
            }
        });
    }

    public void openTripLogsActivity() {
        Intent intent = new Intent(this, TripLogActivity.class);
        startActivity(intent);
    }

    public void openExpenditureLogsActivity() {
        startActivity(new Intent(this,ExpenditureLogActivity.class));
    }
}