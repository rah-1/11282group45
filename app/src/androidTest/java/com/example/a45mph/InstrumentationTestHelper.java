package com.example.a45mph;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class InstrumentationTestHelper {
    public static boolean setUpFile(String filePath) {
        try {
            File f = new File(filePath);
            if(f.delete()) {
                Log.d("File Man", "File Existed");
            }
            return f.createNewFile();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testTransfer(DataLog transLog, Scanner s) {
        try {
            String compareString = s.nextLine();
            return Objects.equals(transLog.toString(), compareString + "\n");
        } catch (Exception e) {
            return false;
        }
    }

    // support for old calls that always failed
    public static boolean exceptionHandler(@NonNull Exception e)
    {
        return exceptionHandler(e,false);
    }

    // handles testing exceptions
    public static boolean exceptionHandler(@NonNull Exception e, boolean pass)
    {
        Log.d("Test Exception", e.toString());
        return pass;
    }
}
