package com.example.a45mph;

public class PersonalizedReport {
    private int performanceRating;
    private double totalFuelConsumption;
    private double totalFuelCost;
    private double averageGasMileage;
    private double environmentalImpact;
    private String reportStartDate;
    private String reportEndDate;

    public PersonalizedReport() {
        performanceRating = 1;
        totalFuelConsumption = 0.0;
        totalFuelCost = 0.0;
        averageGasMileage = 0.0;
        environmentalImpact = 0.0;
    }

    public int getPerformanceRating() {return performanceRating;}
    public double getTotalFuelConsumption() {return totalFuelConsumption;}
    public double getTotalFuelCost() {return totalFuelCost;}
    public double getAverageGasMileage() {return averageGasMileage;}
    public double getEnvironmentalImpact() {return environmentalImpact;}
    public String getReportStartDate() {return reportStartDate;}
    public String getReportEndDate() {return reportEndDate;}
    public String getRatingAsString() {return Integer.toString(performanceRating)+"/5 stars";}

    public void setPerformanceRating(int PR) {performanceRating=PR;}
    public void setTotalFuelConsumption(double TFC) {totalFuelConsumption=TFC;}
    public void setTotalFuelCost(double TFC) {totalFuelCost=TFC;}
    public void setAverageGasMileage(double AGM) {averageGasMileage=AGM;}
    public void setEnvironmentalImpact(double EI) {environmentalImpact=EI;}
    public void setReportStartDate(String date) {reportStartDate=date;}
    public void setReportEndDate(String date) {reportEndDate=date;}

}
