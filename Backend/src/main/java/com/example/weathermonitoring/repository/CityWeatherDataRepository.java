package com.example.weathermonitoring.repository;

import com.example.weathermonitoring.entity.CityWeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityWeatherDataRepository extends JpaRepository<CityWeatherData, Long> {

    Optional<CityWeatherData> findByCity(String city);
}
