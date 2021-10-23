package com.example.a45mph;

import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

public class VehicleProfileTests {

    @Test
    public void testVehicleProfileCreation()
    {
        VehicleProfile vp = VehicleProfile.generateProfile("This", "That", "This and That");
        assert Objects.equals(vp.getName(),"This and That");
        assert Objects.equals(vp.getMake(), "This");
        assert Objects.equals(vp.getModel(), "That");
    }

}
