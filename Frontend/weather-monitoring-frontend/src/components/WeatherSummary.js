// src/components/WeatherSummary.js
import React, { useEffect, useState } from 'react';
import { getWeatherSummary } from '../services/weatherService';

const WeatherSummary = () => {
  const [summary, setSummary] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await getWeatherSummary();
      setSummary(result.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h2>Daily Weather Summary</h2>
      <ul>
        {summary.map((item) => (
          <li key={item.city}>
            {item.city}: {item.averageTemperature}°C (Min: {item.minTemperature}°C, Max: {item.maxTemperature}°C)
          </li>
        ))}
      </ul>
    </div>
  );
};

export default WeatherSummary;
