// src/services/weatherService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/weather';

export const getWeatherForCity = async (city) => {
  return await axios.get(`${API_URL}/${city}`);
};

export const getWeatherSummary = async () => {
  return await axios.get(`${API_URL}/summary`);
};

export const getHistoricTrends = async (city) => {
  return await axios.get(`${API_URL}/${city}`);
};

export const getTriggeredAlerts = async () => {
  return await axios.get(`${API_URL}/alerts`);
};
