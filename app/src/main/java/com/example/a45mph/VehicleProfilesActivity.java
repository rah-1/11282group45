package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleProfilesActivity extends AppCompatActivity {

    private EditText makeText;
    private EditText modelText;
    private EditText nameText;
    private EditText year;
    private RadioButton automatic;
    private RadioButton manual;
    private EditText speed;
    private RadioButton gas;
    private RadioButton diesel;
    private RadioButton electricity;
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

        speed = (EditText) findViewById(R.id.speededittextvehicleprofiles);
        automatic = (RadioButton) findViewById(R.id.automaticbuttonvehicleprofiles);
        manual = (RadioButton) findViewById(R.id.manualbuttonvehicleprofiles);
        year = (EditText) findViewById(R.id.yearedittextvehicleprofiles);
        gas = (RadioButton) findViewById(R.id.gasbuttonvehicleprofiles);
        diesel = (RadioButton) findViewById(R.id.dieselbuttonvehicleprofiles);
        electricity = (RadioButton) findViewById(R.id.electricityradiobuttonvehicleprofiles);
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
            String make = makeText.getText().toString();
            String model = modelText.getText().toString();
            String name = nameText.getText().toString();
            String year = this.year.getText().toString();
            boolean auto = automatic.isChecked();
            boolean man = manual.isChecked();
            boolean gas = this.gas.isChecked();
            boolean diesel = this.diesel.isChecked();
            boolean electricity = this.electricity.isChecked();
            String speed = this.speed.getText().toString();

            // read data out of the economy file to make a new vehicle
            Scanner s = new Scanner(getResources().openRawResource(R.raw.fueleconomy));
            s.nextLine(); // skips first line of the file

            // search through the file for a record that matches with the given one,
            // create profile in current system, and return result
            VehicleProfile vp = VehicleProfile.searchProfile(make, model, name, year, gas,
                    diesel, electricity, auto, man, speed, s);

            if (vp == null)
                throw new IOException("Duplicate Profile Name");

            vp.transfer(this.getFileStreamPath(VehicleProfile.FILE));
            VehicleSelectionActivity.profileAdapter.selectProfile(VehicleSelectionActivity.profileAdapter.addProfile(vp));

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
            errorMessage = "Error: " + e.getMessage();
        } catch (Exception e) {
            // write to the screen that something unexplained has happened
            errorMessage = "Error: Something Went Wrong";
        }

        Log.d("Vehicle Profile", errorMessage);
        if (!noExceptions) {
            Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        }
    }
}