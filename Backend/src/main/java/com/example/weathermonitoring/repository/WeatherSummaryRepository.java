package com.example.weathermonitoring.repository;

import com.example.weathermonitoring.entity.WeatherData;
import com.example.weathermonitoring.entity.WeatherSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherSummaryRepository extends JpaRepository<WeatherSummary, Long> {
    Optional<WeatherSummary> findByCityAndDate(String city, LocalDate date);

    @Query(value = "SELECT * FROM weather_data WHERE city = :city ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
    Optional<WeatherData> findTopByCityOrderByTimestampDesc(@Param("city") String city);

}