package com.example.a45mph;

import static org.junit.Assert.assertEquals;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FuelCostInstrumentTests {

    private final String FILEPATH = "/data/data/com.example.a45mph/expendLog.csv";

    private boolean setupTests(FuelCostActivity fca, String unitPrice, String amtBought, boolean isHypothetical)
    {
        try
        {
            ((EditText) fca.findViewById(R.id.fuelamountfield)).setText(unitPrice);
            ((EditText) fca.findViewById(R.id.unitcostfield)).setText(amtBought);
            ((RadioButton) fca.findViewById(R.id.ishypothetical)).setChecked(isHypothetical);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myapplication", appContext.getPackageName());
    }

    @Test
    public void testFuelCostCalculation() {
        FuelCostActivity fca = new FuelCostActivity();

        // test calculation
        assert setupTests(fca,"2.5","50",false);
        fca.calculateCost(false);
        String tester = ((TextView) fca.findViewById(R.id.resulttext)).getText().toString();
        assertEquals("125.00",tester);
    }

    @Test
    public void testFuelCostLogging() {
        FuelCostActivity fca = new FuelCostActivity();

        assert setupTests(fca,"3","20",false);
        double result = fca.calculateCost(false);
        fca.calculateCost(false);
        String tester = ((TextView) fca.findViewById(R.id.resulttext)).getText().toString();
        assertEquals("60",tester);

        ExpenditureDataLog expenditure = FuelCalculators.getFuelExpenditure().get(0);

        try {
            assert InstrumentationTestHelper.setUpFile(FILEPATH);

            expenditure.transfer();
            File f = new File(FILEPATH);
            Scanner s = new Scanner(f);

            assert InstrumentationTestHelper.testTransfer(expenditure,s);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testHypotheticalFuelCostCalculation() {
        FuelCostActivity fca = new FuelCostActivity();

        // test calculation
        assert setupTests(fca,"2.5","50",false);
        fca.calculateCost(false);
        String tester = ((TextView) fca.findViewById(R.id.resulttext)).getText().toString();
        assertEquals("125.00",tester);
    }


}
