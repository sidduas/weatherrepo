package com.java.weatherreport.restapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java.weatherreport.common.WeatherRestApi;
import com.java.weatherreport.data.CustomReport;
import com.java.weatherreport.data.WeatherReport;
import com.java.weatherreport.data.list;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class WeatherForecastRestController {

    private WeatherRestApi weatherRestApi;

    @GetMapping("wetherreport")
    List<CustomReport> getWeatherReport(@RequestParam(value = "city", defaultValue = "Bangalore") String cityName) {
        List<CustomReport> customReportList = new ArrayList<>();
        String report = "";
        //Call third party API to get forecast details.
        try {
            report = weatherRestApi.getWeatherReport(cityName).getBody();
        } catch (Exception e) {
            CustomReport customReport = new CustomReport();
            customReport.setAdviceMessage("City not Found");
            customReportList.add(customReport);
            return customReportList;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        WeatherReport weatherReport = gson.fromJson(report, WeatherReport.class);
        List<list> listList = weatherReport.getList();

        for (list list : listList) {
            customReportList.add(weatherRestApi.getCustomReport(list));
        }

        System.out.println(customReportList);
        return customReportList;
    }


}
