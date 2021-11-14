package com.example.a45mph;

import java.util.Objects;

public class Fueltype {
    private String typename;
    private String entry;

    Fueltype()
    {
        typename = "null";
        entry = "null";
    }

    Fueltype(String ent)
    {
        typename = transEntry(ent);
        entry = ent;
    }

    public String toString()
    {
        return entry;
    }

    public static String transEntry(String ent)
    {
        String type = "null";

        if (Objects.equals(ent,"Regular") || Objects.equals(ent,"Premium")
        || Objects.equals(ent,"Regular Gas") || Objects.equals(ent, "Premium Gas"))
            type = "gas";
        else if (Objects.equals(ent, "Diesel"))
            type = "diesel";
        else if (Objects.equals(ent,"Electricity"))
            type = "electric";
        else if (Objects.equals(ent, "Invalid"))
            type = "invalid";

        return type;
    }

}
