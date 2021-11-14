package com.example.a45mph;

import android.os.Build;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.time.LocalDateTime;

public class EmissionDataLog extends DataLog {
    double gCO2;
    public final String FILE = "emissions.csv";

    private void setAll(double gCO2){
        this.gCO2 = gCO2;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    EmissionDataLog(double gCO2) {
        super(LocalDateTime.now(),VehicleProfileAdapter.currentProfile);
        setAll(gCO2);
    }

    EmissionDataLog(double gCO2, LocalDateTime time) {
        super(time);
        setAll(gCO2);
    }

    EmissionDataLog(double gCO2, LocalDateTime time, VehicleProfile vp) {
        super(time,vp);
        setAll(gCO2);
    }

    public double getgCO2() {
        return Math.round(gCO2 * 100) / 100.0;
    }

    @Override
    public void setEntry() {
        entry =  time + "," + vehicle.getName() + "," + gCO2 + "\n";
    }

    @Override
    public void transfer() throws IOException {
        // implement to save emissions data later.
    }
}
