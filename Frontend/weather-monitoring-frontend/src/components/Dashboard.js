// src/components/Dashboard.js
import React, { useState } from 'react';
import WeatherSummary from './WeatherSummary';
import HistoricTrends from './HistoricTrends';
import TriggeredAlerts from './TriggeredAlerts';

const Dashboard = () => {
  const [selectedCity, setSelectedCity] = useState('Delhi');

  return (
    <div>
      <h1>Weather Monitoring Dashboard</h1>
      <WeatherSummary />
      <TriggeredAlerts />
      <select onChange={(e) => setSelectedCity(e.target.value)}>
        <option value="Delhi">Delhi</option>
        <option value="Mumbai">Mumbai</option>
        <option value="Chennai">Chennai</option>
        <option value="Bangalore">Bangalore</option>
        <option value="Kolkata">Kolkata</option>
      </select>
      <HistoricTrends city={selectedCity} />
    </div>
  );
};

export default Dashboard;
