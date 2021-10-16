package com.example.a45mph;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.time.LocalDateTime;

public abstract class Log
{
    protected LocalDateTime time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Log()
    {
        time = LocalDateTime.now();
    }

    public Log(LocalDateTime time)
    {
        this.time = time;
    }

    public LocalDateTime getTime() { return time; }
}
