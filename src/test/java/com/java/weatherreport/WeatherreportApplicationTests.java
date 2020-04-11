package com.java.weatherreport;

import com.java.weatherreport.common.WeatherRestApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class WeatherreportApplicationTests {

	@Mock
	RestTemplate restTemplate = new RestTemplate();
	@Test
	void testGetDate() {
		WeatherRestApi weatherRestApi = new WeatherRestApi(restTemplate);
		String formattedDate = weatherRestApi.getDate(1587016800);
		assert formattedDate.equals("16042020");
	}

	@Test
	void testTempInCelcius() {
		WeatherRestApi weatherRestApi = new WeatherRestApi(restTemplate);
		double tempInCelcius = weatherRestApi.tempInCelcius("288.37");
		System.out.println(tempInCelcius);
		assert tempInCelcius == 15.220000000000027;
	}

}
