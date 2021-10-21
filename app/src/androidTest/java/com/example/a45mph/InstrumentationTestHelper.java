package com.example.a45mph;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class InstrumentationTestHelper {
    public static boolean setUpFile(String filePath) {
        try {
            File f = new File("/data/data/com.example.a45mph/tripLog.csv");
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
            return Objects.equals(transLog.toString(),s.nextLine() + "\n");
        } catch (Exception e) {
            return false;
        }
    }
}
