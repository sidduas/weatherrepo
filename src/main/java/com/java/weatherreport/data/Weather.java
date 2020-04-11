package com.java.weatherreport.data;

import lombok.Data;

@Data
public class Weather {
    String id;
    String main;
    String description;

    public void setId(String id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }


}
