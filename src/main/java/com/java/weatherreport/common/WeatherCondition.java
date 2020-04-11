package com.java.weatherreport.common;

public enum WeatherCondition {

    CLEAR("Clear"),
    CLOUDS("Clouds");

    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }



    WeatherCondition(String condition) {
        this.condition = condition;
    }
}
