package com.java.weatherreport.restapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java.weatherreport.common.WeatherCondition;
import com.java.weatherreport.common.WeatherRestApi;
import com.java.weatherreport.data.CustomReport;
import com.java.weatherreport.data.WeatherReport;
import com.java.weatherreport.data.list;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class WeatherForecastRestController {

    private WeatherRestApi weatherRestApi;

    @GetMapping("wetherreport")
    List<CustomReport> getWeatherReport(@RequestParam(value = "city", defaultValue = "Bangalore") String cityName) {
        String report = weatherRestApi.getWeatherReport(cityName).getBody();
        System.out.println(report);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WeatherReport weatherReport = gson.fromJson(report, WeatherReport.class);
        List<CustomReport> customReportList = new ArrayList<>();
        List<list> listList = weatherReport.getList();
        for (list list : listList) {
            customReportList.add(getCustomReport(list));
        }

        System.out.println(customReportList);
        return customReportList;
    }

    private static CustomReport getCustomReport(list list) {
        CustomReport customReport = new CustomReport();
        double minTemp = tempInCelcius(list.getMain().getTemp_min());
        double maxTemp = tempInCelcius(list.getMain().getTemp_max());
        customReport.setMinTemperature(minTemp);
        customReport.setMaxTemperature(maxTemp);
        customReport.setLocalDate(new Date(list.getDt()));

        Optional<String> weatherCondition = Optional.ofNullable(list)
                .map(com.java.weatherreport.data.list::getWeather)
                .map(weathers -> weathers.get(0).getMain());

        if (maxTemp > 40 && WeatherCondition.CLEAR.getCondition().equals(weatherCondition.get())) {
            customReport.setAdviceMessage("Use sunscreen lotion");
        } else if (weatherCondition.isPresent() && WeatherCondition.CLOUDS.getCondition().equals(weatherCondition.get())) {
            customReport.setAdviceMessage("Carry umbrella");
        }
        return customReport;
    }

    private static double tempInCelcius(String tempF) {
        return ((Double.parseDouble(tempF) - 32) * 5) / 9;
    }

}
