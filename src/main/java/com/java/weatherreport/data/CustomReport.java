package com.java.weatherreport.data;

public class CustomReport {
    private double minTemperature;
    private double maxTemperature;
    private String adviceMessage;
    private String localDate;

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomReport that = (CustomReport) o;

        if (Double.compare(that.minTemperature, minTemperature) != 0) return false;
        if (Double.compare(that.maxTemperature, maxTemperature) != 0) return false;
        if (adviceMessage != null ? !adviceMessage.equals(that.adviceMessage) : that.adviceMessage != null)
            return false;
        return localDate != null ? localDate.equals(that.localDate) : that.localDate == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(minTemperature);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (adviceMessage != null ? adviceMessage.hashCode() : 0);
        result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
        return result;
    }
}
