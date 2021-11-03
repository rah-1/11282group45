package com.example.a45mph;

import org.junit.Test;

public class GasMileageTests {
    @Test
    public void executeGasMileage(){
        assert(30 / 2 == FuelCalculators.gasMileage(2, 30));
        assert(240 / 40 == FuelCalculators.gasMileage(40, 220));
        assert(5 / 25 == FuelCalculators.gasMileage(25, 5));
    }

    @Test
    public void testGasMileageRounding(){
        assert(0.51 == FuelCalculators.gasMileage(1, 0.506));
        assert(4.77 == FuelCalculators.gasMileage(1, 4.772));
    }

    @Test
    public void testGasMileageZero(){
        try{
            double badInput = FuelCalculators.gasMileage(0,5);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
        try{
            double badInput = FuelCalculators.gasMileage(5,0);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
        try{
            double badInput = FuelCalculators.gasMileage(0,0);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
    }

    @Test
    public void testGasMileageNegative(){
        try{
            double badInput = FuelCalculators.gasMileage(7,-9);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
        try{
            double badInput = FuelCalculators.gasMileage(-7,9);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
        try{
            double badInput = FuelCalculators.gasMileage(-7,-9);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
    }

}
