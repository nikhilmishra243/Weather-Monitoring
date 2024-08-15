package com.example.weathermonitoring.service;

import com.example.weathermonitoring.entity.CityWeatherData;
import com.example.weathermonitoring.entity.WeatherData;
import com.example.weathermonitoring.entity.WeatherSummary;
import com.example.weathermonitoring.repository.CityWeatherDataRepository;
import com.example.weathermonitoring.repository.WeatherSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@Service
public class WeatherService {

    @Autowired
    private WeatherSummaryRepository weatherSummaryRepository;


    @Autowired
    private CityWeatherDataRepository cityWeatherDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final List<String> CITIES = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata");
    private static final String API_KEY = "9d06d9b4825f10f79591ff063769f070";
//    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s" + API_KEY;
    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=9d06d9b4825f10f79591ff063769f070";

    private static final double ALERT_THRESHOLD = 29.0;


    public WeatherData getWeatherData(String city) {


        // Format the API URL with the city name and API key
        String url = String.format(WEATHER_API_URL, city, API_KEY);

        // Call the OpenWeatherMap API and retrieve the weather data
        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
        weatherData.getMain().setTemp((int) (weatherData.getMain().getTemp()-273));
        weatherData.getMain().setFeels_like((int) (weatherData.getMain().getFeels_like()-273));


        // Return the weather data
        return weatherData;
    }

    @Scheduled(fixedRate = 2000)  // 5 minutes in milliseconds
    public void fetchAndSaveWeatherData() {
        for (String city : CITIES) {
            String url = String.format(WEATHER_API_URL, city);
            WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
            if (weatherData != null) {
                updateWeatherSummary(weatherData, city);
            }
        }
    }

    private void updateWeatherSummary(WeatherData weatherData, String city) {
        LocalDate today = LocalDate.now();
        Optional<WeatherSummary> optionalSummary = weatherSummaryRepository.findByCityAndDate(city, today);

        WeatherSummary summary;
        if (optionalSummary.isPresent()) {
            summary = optionalSummary.get();
        } else {
            summary = new WeatherSummary();
            summary.setCity(city);
            summary.setDate(today);
            summary.setMinTemperature(Double.MAX_VALUE); // Set initial min temperature to a large value
        }

        // Convert temperature from Kelvin to Celsius
        double tempInCelsius = weatherData.getMain().getTemp() - 273.15;

        // Update min, max, and average temperatures
        if (tempInCelsius > summary.getMaxTemperature()) {
            summary.setMaxTemperature(tempInCelsius);
        }
        if (tempInCelsius < summary.getMinTemperature()) {
            summary.setMinTemperature(tempInCelsius);
        }

        // Update average temperature (simple average example)
        double currentAvg = summary.getAverageTemperature();
        int count = 1; // Use appropriate logic to count records
        summary.setAverageTemperature((currentAvg * count + tempInCelsius) / (count + 1));

        weatherSummaryRepository.save(summary);
    }

    @Scheduled(fixedRate = 2000)  // 10 minutes in milliseconds
    public void fetchAndStoreWeatherData() {
        for (String city : CITIES) {
            String url = String.format(WEATHER_API_URL, city);
            WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);

            if (weatherData != null && weatherData.getMain() != null) {
                double tempInKelvin = weatherData.getMain().getTemp();
                double tempInCelsius = tempInKelvin - 273.15;

                Optional<CityWeatherData> existingRecordOptional = cityWeatherDataRepository.findByCity(city);

                CityWeatherData cityWeatherData;
                if (existingRecordOptional.isPresent()) {
                    cityWeatherData = existingRecordOptional.get();

                    // Check if both the current and new temperature exceed the threshold
                    if (cityWeatherData.getTemperature() > ALERT_THRESHOLD && tempInCelsius > ALERT_THRESHOLD) {
                        // Trigger alert
                        System.out.println("ALERT: High temperature detected in " + city +
                                " with current temp: " + cityWeatherData.getTemperature() +
                                "°C and new temp: " + tempInCelsius + "°C");
//                        throw new RuntimeException("EXCEPTION");
                    }

                } else {
                    cityWeatherData = new CityWeatherData();
                    cityWeatherData.setCity(city);
                }

                cityWeatherData.setTimestamp(LocalDateTime.now());
                cityWeatherData.setTemperature(tempInCelsius);

                cityWeatherDataRepository.save(cityWeatherData);

            }
        }
    }
//    }
//
//    private void sendAlert(String city, double currentTemperature, double previousTemperature) {
//        System.out.printf("ALERT: In %s, the current temperature (%.2f°C) and the previous temperature (%.2f°C) both exceed the threshold of %.2f°C%n",
//                city, currentTemperature, previousTemperature, ALERT_THRESHOLD);
//    }


}