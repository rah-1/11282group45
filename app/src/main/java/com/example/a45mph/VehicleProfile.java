package com.example.a45mph;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleProfile extends DataLog {
    public static final String FILEPATH = "/data/data/com.example.a45mph/vehicles.csv";
    private String vehicleID;
    private String vehicleName;
    private String make;
    private String model;
    private Fueltype fueltype;
    private double gramsPerMileCO2;

    public VehicleProfile()
    {
        vehicleID = "0";
        vehicleName = "null";
        make = "null";
        model = "null";
        gramsPerMileCO2 = -1;
        fueltype = new Fueltype("null");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static VehicleProfile readProfile(Scanner lineScanner)
    {
        LocalDateTime timestamp = LocalDateTime.parse(lineScanner.next());
        String name = lineScanner.next();
        String id = lineScanner.next();
        String make = lineScanner.next();
        String model = lineScanner.next();
        Fueltype fuel = new Fueltype(lineScanner.next());
        double gpm = Double.parseDouble(lineScanner.next());
        return new VehicleProfile(make, model, name, id, fuel, gpm, timestamp);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<VehicleProfile> loadVehicleProiles() throws IOException
    {
        ArrayList<VehicleProfile> profiles = new ArrayList<>();
        Scanner s = new Scanner(new File(FILEPATH));
        // iterate through the file and read the vehicle profiles
        try {
            while (s.hasNextLine())
            {
                // set up Scanner with comma delimiter
                String line = s.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                // read out all the attributes of the profile
                VehicleProfile profile = readProfile(lineScanner);
                profiles.add(profile);
            }

            return profiles;

        } catch (Exception e) {
            Log.d("Profile Selection","IOException Thrown");
            throw new IOException();
        }
    }

    public VehicleProfile(String make, String model, String name)
    {
        this.make = make;
        this.model = model;
        this.vehicleName = name;
        setVehicleData(make, model);
    }

    public VehicleProfile(String make, String model, String name, String id, Fueltype fuel, double gCO2, LocalDateTime time)
    {
        super(time);
        vehicleID = id;
        vehicleName = name;
        this.make = make;
        this.model = model;
        gramsPerMileCO2 = gCO2;
        fueltype = fuel;
    }

    public String getName() { return vehicleName; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getVehicleID() { return vehicleID; }

    private void setVehicleData(String make, String model)
    {
        // get vehicle data out of the csv
        // TODO: replace these defaults when we have the csv ready and implemented
        vehicleID = "0";
        gramsPerMileCO2 = -1;
        fueltype = new Fueltype("null");
    }

    public static VehicleProfile generateProfile(String make, String model, String name)
    {
        return new VehicleProfile(make, model, name);
    }

    public void setEntry()
    {
        entry = time + "," + vehicleName + "," + vehicleID + "," + make + "," + model +
                "," + fueltype + "," + gramsPerMileCO2 + "\n";
    }

    public void transfer() throws IOException
    {
        File vehicleFile = new File(FILEPATH);
        setEntry();
        Log.d("File Man", vehicleFile.getAbsoluteFile().toString());
        Log.d("File Man", entry);

        transfer(vehicleFile);
    }

}
