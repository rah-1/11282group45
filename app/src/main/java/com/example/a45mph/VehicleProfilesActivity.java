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
            String speed = this.speed.getText().toString();
            String yearString = this.year.getText().toString();
            String concat = make+model+name+speed+yearString;

            if (concat.contains(","))
            {
                throw new IOException("Do not use commas!");
            }

            boolean auto = automatic.isChecked();
            boolean man = manual.isChecked();
            boolean gas = this.gas.isChecked();
            boolean diesel = this.diesel.isChecked();
            boolean electricity = this.electricity.isChecked();

            int parsedYear = 0;

            try {
                parsedYear = Integer.parseInt(yearString);
            } catch (NumberFormatException e)
            {
                throw new IOException("Year Not Formatted Correctly");
            }

            Scanner s = parseYear(parsedYear);

            // read data out of the economy file to make a new vehicle
            if (s == null)
                throw new IOException("Select a year after 1984 and before 2023!");

            s.nextLine(); // skips first line of the file

            // search through the file for a record that matches with the given one,
            // create profile in current system, and return result
            VehicleProfile vp = VehicleProfile.searchProfile(make, model, name, yearString, gas,
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

    private Scanner parseYear(int parsedYear)
    {
        Scanner s;
        switch (parsedYear) {
            case 1985:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1985));
                break;
            case 1986:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1986));
                break;
            case 1987:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1987));
                break;
            case 1988:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1988));
                break;
            case 1989:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1989));
                break;
            case 1990:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1990));
                break;
            case 1991:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1991));
                break;
            case 1992:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1992));
                break;
            case 1993:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1993));
                break;
            case 1994:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1994));
                break;
            case 1995:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1995));
                break;
            case 1996:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1996));
                break;
            case 1997:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1997));
                break;
            case 1998:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1998));
                break;
            case 1999:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy1999));
                break;
            case 2000:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2000));
                break;
            case 2001:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2001));
                break;
            case 2002:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2002));
                break;
            case 2003:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2003));
                break;
            case 2004:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2004));
                break;
            case 2005:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2005));
                break;
            case 2006:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2006));
                break;
            case 2007:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2007));
                break;
            case 2008:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2008));
                break;
            case 2009:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2009));
                break;
            case 2010:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2010));
                break;
            case 2011:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2011));
                break;
            case 2012:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2012));
                break;
            case 2013:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2013));
                break;
            case 2014:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2014));
                break;
            case 2015:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2015));
                break;
            case 2016:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2016));
                break;
            case 2017:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2017));
                break;
            case 2018:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2018));
                break;
            case 2019:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2019));
                break;
            case 2020:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2020));
                break;
            case 2021:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2021));
                break;
            case 2022:
                s = new Scanner(getResources().openRawResource(R.raw.fueleconomy2022));
                break;
            default:
                s = null;
                break;
        }

        return s;
    }

}