package com.example.a45mph;

import org.junit.Test;

public class GasMileageTests {
    @Test
    public void executeGasMileage(){
        assert(30 / 2 == FuelCalculators.gasMileage( 30,2));
        assert(220.0 / 40.0 == FuelCalculators.gasMileage(220,40));
        assert(25 / 5 == FuelCalculators.gasMileage(25, 5));
    }

    @Test
    public void testGasMileageRounding(){
        assert(0.51 == FuelCalculators.gasMileage(0.506, 1));
        assert(4.77 == FuelCalculators.gasMileage(4.772, 1));
    }

    @Test
    public void testGasMileageZero(){
        try{
            double badInput = FuelCalculators.gasMileage(5,0);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }

        try{
            double badInput = FuelCalculators.gasMileage(0,5);
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
            double badInput = FuelCalculators.gasMileage(-9, 7);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }

        try{
            double badInput = FuelCalculators.gasMileage(9, -7);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }

        try{
            double badInput = FuelCalculators.gasMileage(-9, -7);
            assert false;
        }
        catch (ArithmeticException e){
            assert true;
        }
    }

}
