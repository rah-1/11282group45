package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EnvironmentalTrackingActivity extends AppCompatActivity {
    private Button personalizedReportsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_tracking);

        personalizedReportsButton = (Button) findViewById(R.id.personalizedreportsbutton);
        personalizedReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalizedReportsActivity();
            }
        });
    }

    public void personalizedReportsActivity() {
        Intent intent = new Intent(this, PersonalizedReportsActivity.class);
        startActivity(intent);
    }
    // devise methods for viewing environmental impact
}