package com.example.a45mph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleProfilesActivity extends AppCompatActivity {
    private final String FILEPATH = "/data/data/com.example.a45mph/vehicles.csv";

    private EditText makeText;
    private EditText modelText;
    private EditText nameText;
    private ImageButton createButton;
    private static ArrayList<VehicleProfile> vehicleArray;

    private void loadVehicleProiles()
    {
        Scanner s = new Scanner(FILEPATH);
        // iterate through the file and read the vehicle profiles
        while (s.hasNextLine())
        {
            // set up Scanner with comma delimiter
            String line = s.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            // read out all the attributes of the profile
            // replace this skeleton implementation with a real version
            vehicleArray.add(new VehicleProfile("This","That","This and That"));
        }
    }

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

}