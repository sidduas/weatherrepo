package com.java.weatherreport.common;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class WeatherRestApi {

    private RestTemplate restTemplate;

    public ResponseEntity<String> getWeatherReport(String city) {
        final String uri = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=d2929e9483efc82c82c32ee7e02d563e";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
    }
}
