package com.example.a45mph;

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

    static String transEntry(String ent)
    {
        // translate a DB entry into its given name
        return "null";
    }

}
