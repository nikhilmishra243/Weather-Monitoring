package com.example.weathermonitoring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "weather_summary")
@Data
public class WeatherSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "average_temperature")
    private double averageTemperature;

    @Column(name = "max_temperature")
    private double maxTemperature;

    @Column(name = "min_temperature")
    private double minTemperature;
}
