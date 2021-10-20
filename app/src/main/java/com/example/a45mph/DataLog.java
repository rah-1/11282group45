package com.example.a45mph;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class DataLog
{
    protected LocalDateTime time;
    protected String entry;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DataLog()
    {
        time = LocalDateTime.now();
        entry = "";
    }

    public DataLog(LocalDateTime time)
    {
        this.time = time;
        entry = "";
    }

    public LocalDateTime getTime() { return time; }

    public abstract void setEntry();
    public abstract void transfer() throws IOException;

    @Override
    public String toString() { return entry; }
}
