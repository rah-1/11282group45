package com.example.a45mph;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ExpenditureDataLog extends DataLog {
    public static final String FILEPATH = "/data/data/com.example.a45mph/expendLog.csv";

    private double expend;
    private double amtBought;

    private void setAll(double ex, double amt)
    {
        expend = ex;
        amtBought = amt;
    }

    ExpenditureDataLog() {
        setAll(0,0);
    }

    ExpenditureDataLog(double ex, double amt, LocalDateTime time)
    {
        super(time);
        setAll(ex,amt);
    }

    ExpenditureDataLog(double ex, double amt, LocalDateTime time, VehicleProfile vehicle)
    {
        super(time, vehicle);
        setAll(ex,amt);
    }

    public double getExpenditure() {
        return expend;
    }

    public double getAmountBought() {
        return amtBought;
    }

    @Override
    public void setEntry() {
        entry = time + "," + vehicle.getName() + "," + expend + "," + amtBought + "\n";
    }

    @Override
    public void transfer() throws IOException {
        File expendLogFile = new File(FILEPATH);
        setEntry();
        Log.d("File Man", expendLogFile.getAbsoluteFile().toString());
        Log.d("File Man", entry);

        transfer(expendLogFile);
    }
}
