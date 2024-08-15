package com.example.weathermonitoring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class

WeatherData {

	@JsonProperty("main")
	private Main main;

	@JsonProperty("weather")
	private List<Weather> weather;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public static class Main {
		private double temp;
		private double feels_like;

		public double getTemp() {
			return temp;
		}

		public void setTemp(double temp) {
			this.temp = temp;
		}

		public double getFeels_like() {
			return feels_like;
		}

		public void setFeels_like(double feels_like) {
			this.feels_like = feels_like;
		}
	}

	public static class Weather {
		private String main;

		public String getMain() {
			return main;
		}

		public void setMain(String main) {
			this.main = main;
		}
	}
}
