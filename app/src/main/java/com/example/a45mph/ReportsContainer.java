package com.example.a45mph;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.IOException;


public class ReportsContainer {
    private boolean reportsOn;
    private boolean enableTotalFuelConsumption;
    private boolean enableTotalFuelCost;
    private boolean enableAverageGasMileage;
    private boolean enableEnvironmentalImpact;
    private String timePeriod;

    public ArrayList<PersonalizedReport> allReports;

    public ReportsContainer() {
        reportsOn = false;
        enableTotalFuelConsumption = true;
        enableTotalFuelCost = true;
        enableAverageGasMileage = true;
        enableEnvironmentalImpact = true;
        timePeriod = "Monthly";
    }

    public boolean isReportsOn () {return reportsOn;}
    public boolean isTotalFuelConsumption () {return enableTotalFuelConsumption;}
    public boolean isTotalFuelCost () {return enableTotalFuelCost;}
    public boolean isAverageGasMileage () {return enableAverageGasMileage;}
    public boolean isEnvironmentalImpact () {return enableEnvironmentalImpact;}
    public String getTimePeriod() {return timePeriod;}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setReportsOn(boolean b) throws IOException {reportsOn=b; recalculateReports();}
    public void setEnableTotalFuelConsumption(boolean b) {enableTotalFuelConsumption=b;}
    public void setEnableTotalFuelCost(boolean b) {enableTotalFuelCost=b;}
    public void setEnableAverageGasMileage(boolean b) {enableAverageGasMileage=b;}
    public void setEnableEnvironmentalImpact(boolean b) {enableEnvironmentalImpact=b;}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTimePeriod(String TP) throws IOException {timePeriod = TP; recalculateReports();}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void recalculateReports() throws IOException {
        allReports.clear();
        if (!reportsOn) return;

        ArrayList<TripDataLog> data = TripDataLog.loadTripDataLogs();
        double gasPrice = 3.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = data.size()-1; i >= 0; i++) {
            double totalConsumption = 0.0;
            int count = 0;
            double totalMileage = 0.0;
            double odometer = 0.0;
            double totalGPM = 0.0;
            String start = "";
            String end = "";
            LocalDateTime startDate = LocalDateTime.now();
            LocalDateTime endDate = LocalDateTime.now();
            switch (timePeriod) {
                case "Daily":
                    startDate =  data.get(i).getTime().truncatedTo(ChronoUnit.DAYS);
                    endDate =  data.get(i).getTime().truncatedTo(ChronoUnit.DAYS).plusDays(1);
                    start = startDate.format(formatter);
                    end = endDate.format(formatter);

                    break;
                case "Weekly":
                    startDate =  data.get(i).getTime().truncatedTo(ChronoUnit.WEEKS);
                    endDate =  data.get(i).getTime().truncatedTo(ChronoUnit.WEEKS).plusWeeks(1);
                    start = startDate.format(formatter);
                    end = endDate.format(formatter);
                    break;
                case "Monthly":
                    startDate =  data.get(i).getTime().truncatedTo(ChronoUnit.MONTHS);
                    endDate =  data.get(i).getTime().truncatedTo(ChronoUnit.MONTHS).plusMonths(1);
                    start = startDate.format(formatter);
                    end = endDate.format(formatter);
                    break;
                case "Annually":
                    startDate =  data.get(i).getTime().truncatedTo(ChronoUnit.YEARS);
                    endDate =  data.get(i).getTime().truncatedTo(ChronoUnit.YEARS).plusYears(1);
                    start = startDate.format(formatter);
                    end = endDate.format(formatter);
                    break;
            }
            while (i>=0 && data.get(i).getTime().isAfter(startDate) && data.get(i).getTime().isBefore(endDate)) {
                totalConsumption+=data.get(i).getConsumption();
                totalMileage+=data.get(i).getMileage();
                odometer+=data.get(i).getOdometer();
                totalGPM+=data.get(i).getVehicle().getGPM();
                count++;
                i--;
            }
            double avgMileage = totalMileage / (double) count;
            double totalFuelCost = gasPrice * odometer / avgMileage;
            double environmentalImpact = totalGPM * odometer / (double) count;
            PersonalizedReport p = new PersonalizedReport();
            double ratingRaw = 2.5*(avgMileage/25.0-environmentalImpact/odometer);
            if (ratingRaw < 0) {ratingRaw=0;}
            if (ratingRaw > 5) {ratingRaw=5;}
            int ratingTrue = (int) ratingRaw;
            p.setPerformanceRating(ratingTrue);
            p.setTotalFuelConsumption(totalConsumption);
            p.setTotalFuelCost(totalFuelCost);
            p.setAverageGasMileage(avgMileage);
            p.setEnvironmentalImpact(environmentalImpact);
            p.setReportStartDate(start);
            p.setReportEndDate(end);
            allReports.add(p);
        }
    }
}
