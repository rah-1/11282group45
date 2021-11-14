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
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class DataLog
{
    protected LocalDateTime time;
    protected VehicleProfile vehicle;
    protected String entry;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DataLog()
    {
        time = LocalDateTime.now();
        vehicle = VehicleProfileAdapter.currentProfile;
        entry = "";
    }

    public DataLog(LocalDateTime t)
    {
        time = t;
        vehicle = VehicleProfileAdapter.currentProfile;
        entry = "";
    }

    public DataLog(LocalDateTime t, VehicleProfile v)
    {
        time = t;
        vehicle = v;
        entry = "";
    }

    public static Scanner makeLineScanner(Scanner s)
    {
        String line = s.nextLine();
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(",");

        return lineScanner;
    }

    public static void skipAttributes(Scanner lineScanner, int lines) throws IOException
    {
        if (lines < 0)
            throw new IOException();

        for (int i = 0; i < lines; i++)
            lineScanner.next();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateAsString(LocalDateTime ldt) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return ldt.format(dateFormatter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getTimeAsString(LocalDateTime ldt) {
        String timePattern = "hh:mm:ss a";
        return ldt.format(DateTimeFormatter.ofPattern(timePattern));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateAndTime(LocalDateTime ldt) {
        return getDateAsString(ldt) + " " + getTimeAsString(ldt);
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
