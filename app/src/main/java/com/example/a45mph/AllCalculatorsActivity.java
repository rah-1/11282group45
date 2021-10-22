package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllCalculatorsActivity extends AppCompatActivity {
    private Button buyableFuelButton;
    private Button fuelCostButton;
    private Button gasMileageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_calculators);

        buyableFuelButton = (Button) findViewById(R.id.buyablefuelbutton);
        buyableFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuyableFuelActivity();
            }
        });

        fuelCostButton = (Button) findViewById(R.id.fuelcostbutton);
        fuelCostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFuelCostActivity();
            }
        });

        gasMileageButton = (Button) findViewById(R.id.gasmileagecalculatorbutton);
        gasMileageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGasMileageActivity();
            }
        });
    }

    public void openBuyableFuelActivity() {
        Intent intent = new Intent(this, HypotheticalBuyableFuelActivity.class);
        startActivity(intent);
    }

    public void openFuelCostActivity() {
        Intent intent = new Intent(this, FuelCostActivity.class);
        startActivity(intent);
    }

    public void openGasMileageActivity() {
        Intent intent = new Intent(this, GasMileageCalculatorActivity.class);
        startActivity(intent);
    }
}