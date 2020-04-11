package com.java.weatherreport.common;

import com.java.weatherreport.data.CustomReport;
import com.java.weatherreport.data.list;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherRestApi {

    private RestTemplate restTemplate;

    public ResponseEntity<String> getWeatherReport(String city) {
        final String uri = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=d2929e9483efc82c82c32ee7e02d563e";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    public CustomReport getCustomReport(list list) {
        CustomReport customReport = new CustomReport();
        double minTemp = tempInCelcius(list.getMain().getTemp_min());
        double maxTemp = tempInCelcius(list.getMain().getTemp_max());
        customReport.setMinTemperature(minTemp);
        customReport.setMaxTemperature(maxTemp);
        customReport.setLocalDate(getDate(list.getDt()));

        Optional<String> weatherCondition = Optional.ofNullable(list)
                .map(com.java.weatherreport.data.list::getWeather)
                .map(weathers -> weathers.get(0).getMain());

        if (weatherCondition.isPresent() && maxTemp > 40 && WeatherCondition.CLEAR.getCondition().equals(weatherCondition.get())) {
            customReport.setAdviceMessage("Use sunscreen lotion");
        } else if (weatherCondition.isPresent() && WeatherCondition.CLOUDS.getCondition().equals(weatherCondition.get())) {
            customReport.setAdviceMessage("Carry umbrella");
        }
        return customReport;
    }

    public double tempInCelcius(String tempF) {
        return Double.parseDouble(tempF) - Double.parseDouble("273.15");
    }

    public String getDate(long epoc) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epoc, 0, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
        return dateTime.format(formatter);
    }
}
