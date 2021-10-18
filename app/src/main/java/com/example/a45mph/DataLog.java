package com.example.a45mph;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.time.LocalDateTime;

public abstract class DataLog
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
}
