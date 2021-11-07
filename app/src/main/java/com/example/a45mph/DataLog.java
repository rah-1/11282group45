package com.example.a45mph;

import android.os.Build;
import androidx.annotation.RequiresApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public abstract class DataLog
{
    protected LocalDateTime time;
    protected VehicleProfile vehicle;
    protected String entry;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DataLog()
    {
        time = LocalDateTime.now();
        vehicle = new VehicleProfile();
        entry = "";
    }

    public DataLog(LocalDateTime t)
    {
        time = t;
        vehicle = new VehicleProfile();
        entry = "";
    }

    public DataLog(LocalDateTime t, VehicleProfile v)
    {
        time = t;
        vehicle = v;
        entry = "";
    }

    public LocalDateTime getTime() { return time; }
    public VehicleProfile getVehicle() { return vehicle; }

    public abstract void setEntry();
    public abstract void transfer() throws IOException;

    protected void transfer(File Logfile) throws IOException {
        if(!Logfile.exists()) {
            if (!Logfile.createNewFile())
            {
                throw new IOException("Unable to Create " + Logfile.getAbsoluteFile().toString());
            }

            Log.d("File Man", "File created successfully");

            FileWriter fw = new FileWriter(Logfile);
            fw.write(entry);
            fw.close();
        }
        else
        {
            Log.d("File Man","File exists");
            FileWriter fw = new FileWriter(Logfile, true);
            fw.write(entry);
            fw.close();
        }
    }

    @Override
    public String toString() { return entry; }

}
