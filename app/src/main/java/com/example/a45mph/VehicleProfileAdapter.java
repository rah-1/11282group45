package com.example.a45mph;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleProfileAdapter extends RecyclerView.Adapter {
    public static final String FILEPATH = "/data/data/com.example.a45mph/currentprofile.csv";
    private ArrayList<VehicleProfile> profiles;
    public static VehicleProfile currentProfile;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public VehicleProfileAdapter()
    {
        try {
            profiles = VehicleProfile.loadVehicleProiles();
            initProfile();
            Log.d("Profile Selection", "Listing Successful");
        } catch (IOException e) {
            profiles = new ArrayList<VehicleProfile>();
            Log.d("Profile Selection", "Listing Unsuccessful");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileHolder(((View) LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProfileHolder profileHolder = (ProfileHolder) holder;
        profileHolder.setName(profiles.get(position).getName());
        profileHolder.setMake(profiles.get(position).getMake());
        profileHolder.setModel(profiles.get(position).getModel());

        profileHolder.getLayout().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                selectProfile(profileHolder.getAdapterPosition());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initProfile() {
        try {
            File f = new File(FILEPATH);

            if (!f.exists())
                throw new IOException("File Did Not Exist");

            Scanner s = new Scanner(f);
            Scanner lineScanner = new Scanner(s.nextLine());
            lineScanner.useDelimiter(",");

            currentProfile = VehicleProfile.readLog(lineScanner);
            Log.d("Profile Init","Initialized Successfully");
        } catch (Exception e) {
            currentProfile = new VehicleProfile();
            Log.d("Profile Init", e.toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void selectProfile(int position) {

        try {
            FileWriter fw = new FileWriter(new File(FILEPATH));
            Scanner s = new Scanner(new File(VehicleProfile.FILEPATH));

            int i = 0;
            while(i++ < position)
                s.nextLine();

            String line = s.nextLine();

            fw.write(line);
            Log.d("Profile Selection", Integer.toString(position));
            Log.d("Profile Selection", line);
            fw.close();
            currentProfile = profiles.get(position);
        } catch (IOException e) {
            Log.d("Profile Selection", "IOException Thrown While Selecting");
            Log.d("Profile Selection", e.toString());
        }
    }

    public ArrayList<VehicleProfile> getProfiles() {
        return profiles;
    }

    public int addProfile(VehicleProfile vp) {
        profiles.add(vp);
        return profiles.size() - 1;
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
