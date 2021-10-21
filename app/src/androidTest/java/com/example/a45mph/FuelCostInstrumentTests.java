package com.example.a45mph;

import static org.junit.Assert.assertEquals;
import android.content.Context;
import android.os.Looper;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.lifecycle.LifecycleRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.Observable;
import java.util.Scanner;

@RunWith(AndroidJUnit4.class)
public class FuelCostInstrumentTests {

    private final String FILEPATH = "/data/data/com.example.a45mph/expendLog.csv";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a45mph", appContext.getPackageName());
    }

}
