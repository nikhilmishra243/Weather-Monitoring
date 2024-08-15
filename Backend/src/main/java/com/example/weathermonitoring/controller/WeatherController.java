package com.example.weathermonitoring.controller;

import com.example.weathermonitoring.entity.WeatherData;
import com.example.weathermonitoring.entity.WeatherSummary;
import com.example.weathermonitoring.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping("/weather/{city}")
	public WeatherData getWeatherForCity(@PathVariable String city) {
		return weatherService.getWeatherData(city);
	}

}
