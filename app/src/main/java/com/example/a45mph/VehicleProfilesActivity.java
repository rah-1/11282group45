package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleProfilesActivity extends AppCompatActivity {
    private final String FILEPATH = "/data/data/com.example.a45mph/vehicles.csv";

    private EditText makeText;
    private EditText modelText;
    private EditText nameText;
    private ImageButton createButton;
    private Button selectButton;
    private static ArrayList<VehicleProfile> vehicleArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profiles);

        createButton = (ImageButton) findViewById(R.id.addvehiclebutton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createVehicleProfile();
            }
        });

        selectButton = (Button) findViewById(R.id.profileselectbutton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVehicleProfile();
            }
        });

        makeText = (EditText) findViewById(R.id.vehicleprofilemake);
        modelText = (EditText) findViewById(R.id.vehicleprofilemodel);
        nameText = (EditText) findViewById(R.id.vehicleprofilename);
        vehicleArray = new ArrayList<VehicleProfile>();
    }

    public static ArrayList<VehicleProfile> getVehicleArray()
    {
        return vehicleArray;
    }

    public static VehicleProfile createVehicleProfile(String make, String model, String name)
    {
        return VehicleProfile.generateProfile(make,model,name);
    }

    public void selectVehicleProfile()
    {
        startActivity(new Intent(this, VehicleSelectionActivity.class));
    }

    // Here, we make and select user's vehicle profiles
    public void createVehicleProfile()
    {
        String errorMessage = "No Error";
        boolean noExceptions = false;


        try {
            // get input from the fields
            VehicleProfile vp = createVehicleProfile(makeText.getText().toString(),modelText.getText().toString(),nameText.getText().toString());
            vp.transfer();

            Log.d("Vehicle Profile", vp.toString());

            Toast.makeText(getApplicationContext(),"Vehicle Profile \"" + vp.getName() + "\" created!",Toast.LENGTH_LONG).show();
            noExceptions = true;
        } catch (NumberFormatException e) {
            // write to the screen that there is an issue with the input
            errorMessage = "Error: Invalid Input!";
        } catch (ArithmeticException e) {
            // write to the screen somewhere that nonpositive amounts for either field are disallowed
            errorMessage = "Error: Arithmetic Error!";
        } catch (IOException e) {
            // write to the screen somewhere that a file error has occurred
            errorMessage = "Error: File IO Error!";
        } catch (Exception e) {
            // write to the screen that something unexplained has happened
            errorMessage = "Error: Something Went Wrong";
        }

        Log.d("Vehicle Profile", errorMessage);
        if (!noExceptions) {
            Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        }
    }

    public static boolean selectVehicleProfile(VehicleProfile vp)
    {
        // change out the current vehicle profile, whatever that entails
        return true;
    }

    public void selectVehicleProfile(int position)
    {
        // use position to select the correct vehicle profile

    }

    public Double getCO2Data(String make, String model, String name, String year, String transmission){
        // create reader for the vehicles.csv file
        InputStream input = getResources().openRawResource(R.raw.vehicles);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));

        String line;
        Double CO2 = -1.0;

        try{
            // read each line looking for a matching vehicle
            while((line = reader.readLine()) != null){
                String[] entries = line.split(",");
                String tempMake = entries[46];
                String tempModel = entries[47];
                String tempYear = entries[63];
                String tempTransmission = entries[57];


                if(tempMake == make && tempModel == model && tempYear == year && tempTransmission == transmission){
                    CO2 = Double.parseDouble(entries[14]);
                    return CO2;
                }
            }
        }
        catch(IOException e){

        }
        return CO2;
    }



}