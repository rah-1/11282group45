package com.example.a45mph;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class EmissionDataLog extends DataLog {
    private double gCO2;
    public final String FILEPATH = "data/data/com.example.a45mph/files/emissions.csv";
    public final String FILE = "emissions.csv";

    private void setAll(double gCO2){
        this.gCO2 = gCO2;
    }

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
        transfer(new File(FILEPATH));
    }
}
