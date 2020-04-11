package com.java.weatherreport.data;

import java.util.List;

public class list {
    long dt;
    List<Weather> weather;
    main main;

    public com.java.weatherreport.data.main getMain() {
        return main;
    }

    public long getDt() {
        return dt;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}
