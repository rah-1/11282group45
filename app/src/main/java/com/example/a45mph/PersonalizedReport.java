package com.example.a45mph;

public class PersonalizedReport {
    private boolean reportsOn;
    private boolean enableTotalFuelConsumption;
    private boolean enableTotalFuelCost;
    private boolean enableAverageGasMileage;
    private boolean enableEnvironmentalImpact;

    private String timePeriod;
    private int performanceRating;
    private double totalFuelConsumption;
    private double totalFuelCost;
    private double averageGasMileage;
    private double environmentalImpact;

    public PersonalizedReport() {
        reportsOn = false;
        enableTotalFuelConsumption = true;
        enableTotalFuelCost = true;
        enableAverageGasMileage = true;
        enableEnvironmentalImpact = true;

        timePeriod = "Monthly";
        performanceRating = 1;
        totalFuelConsumption = 0.0;
        totalFuelCost = 0.0;
        averageGasMileage = 0.0;
        environmentalImpact = 0.0;
    }

    public boolean isReportsOn () {return reportsOn;}
    public boolean isTotalFuelConsumption () {return enableTotalFuelConsumption;}
    public boolean isTotalFuelCost () {return enableTotalFuelCost;}
    public boolean isAverageGasMileage () {return enableAverageGasMileage;}
    public boolean isEnvironmentalImpact () {return enableEnvironmentalImpact;}

    public String getTimePeriod() {return timePeriod;}
    public int getPerformanceRating() {return performanceRating;}
    public double getTotalFuelConsumption() {return totalFuelConsumption;}
    public double getTotalFuelCost() {return totalFuelCost;}
    public double getAverageGasMileage() {return averageGasMileage;}
    public double getEnvironmentalImpact() {return environmentalImpact;}

}
