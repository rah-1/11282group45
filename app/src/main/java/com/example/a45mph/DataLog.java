package com.example.a45mph;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class DataLog extends Application
{
    protected LocalDateTime time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DataLog()
    {
        time = LocalDateTime.now();
    }

    public DataLog(LocalDateTime time)
    {
        this.time = time;
    }

    public LocalDateTime getTime() { return time; }

    public abstract void transfer() throws IOException;
}
