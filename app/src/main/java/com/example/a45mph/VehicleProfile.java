package com.example.a45mph;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class VehicleProfile extends DataLog {
    private final String FILEPATH = "/data/data/com.example.a45mph/vehicles.csv";
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

    public VehicleProfile(String make, String model, String name)
    {
        this.make = make;
        this.model = model;
        this.vehicleName = name;
        setVehicleData(make, model);
    }

    public VehicleProfile(String make, String model, String name, String id, Fueltype fuel, double gCO2)
    {
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