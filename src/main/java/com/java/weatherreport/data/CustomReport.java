package com.java.weatherreport.data;

import java.util.Date;

public class CustomReport {
    private double minTemperature;
    private double maxTemperature;
    private String adviceMessage;
    private Date localDate;

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public String getAdviceMessage() {
        return adviceMessage;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setAdviceMessage(String adviceMessage) {
        this.adviceMessage = adviceMessage;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }
}
