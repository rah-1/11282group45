package com.example.a45mph;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class VehicleProfile extends DataLog {
    public static final String FILEPATH = "/data/data/com.example.a45mph/files/vehicles.csv";
    public static final String FILE = "vehicles.csv";
    private String vehicleID;
    private String vehicleName;
    private String make;
    private String model;
    private int year;
    private Fueltype fueltype;
    private double gramsPerMileCO2;

    private void setAll(String make,String model, String name, int year, String id, Fueltype fuel, double gCO2, LocalDateTime time) {
        vehicleID = id;
        vehicleName = name;
        this.make = make;
        this.model = model;
        gramsPerMileCO2 = gCO2;
        fueltype = fuel;
        this.year = year;
    }

    public VehicleProfile()
    {
        vehicleID = "0";
        vehicleName = "null";
        make = "null";
        model = "null";
        gramsPerMileCO2 = -1;
        fueltype = new Fueltype("null");
    }

    public static VehicleProfile readLog(Scanner lineScanner)
    {
        LocalDateTime timestamp = LocalDateTime.parse(lineScanner.next());
        String name = lineScanner.next();
        String id = lineScanner.next();
        String make = lineScanner.next();
        String model = lineScanner.next();
        int year = Integer.parseInt(lineScanner.next());
        Fueltype fuel = new Fueltype(lineScanner.next());
        double gpm = Double.parseDouble(lineScanner.next());
        return new VehicleProfile(make, model, name, id, fuel, gpm, timestamp);
    }

    public static ArrayList<VehicleProfile> loadVehicleProfiles() throws IOException
    {
        return loadVehicleProfiles(new File(FILEPATH));
    }

    public static ArrayList<VehicleProfile> loadVehicleProfiles(File file) throws IOException
    {
        ArrayList<VehicleProfile> profiles = new ArrayList<>();
        Scanner s = new Scanner(file);
        // iterate through the file and read the vehicle profiles
        try {
            while (s.hasNextLine())
            {
                // set up Scanner and read out all the attributes of the profile
                VehicleProfile profile = readLog(makeLineScanner(s));
                profiles.add(profile);
            }

            return profiles;

        } catch (Exception e) {
            Log.d("Loading Vehicles","IOException Thrown");
            throw new IOException("Error Reading Trips");
        }
    }

    public static VehicleProfile searchProfile(String make, String model, String name, String year, boolean gas,
                                               boolean diesel, boolean elec, boolean auto, boolean man, String speed, Scanner s) throws IOException
    {
        // search for vehicle profile according to the passed params. Construct same and continue
        // with the process of making the profile by passing the result to caller
        if (VehicleSelectionActivity.profileAdapter.searchProfiles(name) != null)
            return null;

        // array list of hits from searching the profiles
        ArrayList<VehicleProfile> hits = new ArrayList<>();

        while(s.hasNextLine())
        {
            Scanner line = makeLineScanner(s);

            skipAttributes(line,4);

            String GramsCO2 = "0";
            String ACO2 = line.next();
            String CO2 = line.next();

            skipAttributes(line, 4);

            Fueltype ft = new Fueltype("Invalid");
            String fuel1 = line.next();
            String fuel2 = line.next();

            if (gas) {

                if (!(Objects.equals(fuel1, "Regular") || Objects.equals(fuel1, "Premium"))
                        && !(Objects.equals(fuel2, "Regular Gas") || Objects.equals(fuel2, "Premium Gas")))
                    continue;

                if (Objects.equals(fuel1, "Regular") || Objects.equals(fuel1, "Premium")) {

                    GramsCO2 = CO2;
                    ft = new Fueltype("Regular");

                } else if (Objects.equals(fuel2, "Regular Gas") || Objects.equals(fuel2, "Premium Gas")) {

                    GramsCO2 = ACO2;
                    ft = new Fueltype("Regular Gas");
                }

            } else if (diesel) {

                if (!(Objects.equals(fuel1, "Diesel") || Objects.equals(fuel2, "Diesel")))
                    continue;

                ft = new Fueltype("Diesel");

                if (Objects.equals(fuel1, "Diesel")) {
                    GramsCO2 = CO2;
                } else if (Objects.equals(fuel2, "Diesel")) {
                    GramsCO2 = ACO2;
                }

            } else if (elec) {
                if (!(Objects.equals(fuel1, "Electricity") || Objects.equals(fuel2, "Electricity")))
                    continue;

                if (Objects.equals(fuel1, "Electricity")) {
                    GramsCO2 = CO2;
                } else if (Objects.equals(fuel2, "Electricity")) {
                    GramsCO2 = ACO2;
                }

            } else {
                throw new IOException("No Gas Type Selected");
            }

            skipAttributes(line,2);

            String id = line.next();
            String fileMake = line.next();

            if (!Objects.equals(make,fileMake))
                continue;

            String fileModel = line.next();
            ArrayList<String> modelList = new ArrayList<>();

            if (fileModel.contains("/"))
            {
                Scanner modelScanner = new Scanner(fileModel);
                modelScanner.useDelimiter("/");

                while (modelScanner.hasNext())
                {
                    modelList.add(modelScanner.next());
                }
            }

            if (modelList.size() == 0) {
                if (!Objects.equals(fileModel,model)) {
                    continue;
                }
            } else {
                boolean match = false;

                for (String cand : modelList)
                {
                    match = match || Objects.equals(cand,model);
                }

                if (!match)
                    continue;
            }

            skipAttributes(line,1);

            String trany = line.next();
            String fileYear = line.next();


            if (!Objects.equals(fileYear,year))
                continue;

            if (auto) {

                if (!trany.contains("Automatic"))
                    continue;

            } else if (man) {

                if(!Objects.equals(trany,"Manual " + speed + "-spd"))
                    continue;

            } else {
                throw new IOException("No Transmission Type Selected");
            }

            // add screened profile to the hits arraylist
            hits.add(new VehicleProfile(make, model, name, Integer.parseInt(year),
                    id,ft,Double.parseDouble(GramsCO2),LocalDateTime.now()));
        }

        if (hits.size() == 0)
            throw new IOException("No Matching Vehicles Records");

        double avgCO2 = 0;
        // synthesize vehicle data from hits
        for (VehicleProfile vp : hits)
        {
            avgCO2 += vp.getCO2();
        }
        avgCO2 /= hits.size();

        return new VehicleProfile(make, model, name, hits.get(0).getYear(),
                hits.get(0).getVehicleID(), hits.get(0).fueltype, avgCO2,LocalDateTime.now());
    }

    public VehicleProfile(String make, String model, String name)
    {
        setAll(make,model,name,-1,"-1",new Fueltype("Invalid"),-1,LocalDateTime.now());
    }

    public VehicleProfile(String make, String model, String name, String id, Fueltype fuel, double gCO2, LocalDateTime time)
    {
        super(time);
        setAll(make,model,name,-1,id,fuel,gCO2,time);
    }

    public VehicleProfile (String make,String model, String name, int year, String id, Fueltype fuel, double gCO2, LocalDateTime time)
    {
        super(time);
        setAll(make,model,name,year,id,fuel,gCO2,time);
    }

    public String getName() { return vehicleName; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getVehicleID() { return vehicleID; }
    public double getCO2() { return gramsPerMileCO2; }
    public int getYear() { return year; }
    public String getFuel() { return fueltype.toString(); }

    public static VehicleProfile generateProfile(String make, String model, String name)
    {
        if (VehicleSelectionActivity.profileAdapter.searchProfiles(name) == null)
            return new VehicleProfile(make, model, name);
        else
            return null;

    }

    public void setEntry()
    {
        entry = time + "," + vehicleName + "," + vehicleID + "," + make + "," + model +
                "," + year + "," + fueltype + "," + gramsPerMileCO2 + "\n";
    }

    public void transfer() throws IOException
    {
        File vehicleFile = new File(FILEPATH);
        Log.d("Vehicle Profile Man", vehicleFile.getAbsoluteFile().toString());
        Log.d("Vehicle Profile Man", entry);

        transfer(vehicleFile);
    }

}
