// src/components/TriggeredAlerts.js
import React, { useEffect, useState } from 'react';
import { getTriggeredAlerts } from '../services/weatherService';

const TriggeredAlerts = () => {
  const [alerts, setAlerts] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await getTriggeredAlerts();
      setAlerts(result.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h2>Triggered Alerts</h2>
      <ul>
        {alerts.map((alert, index) => (
          <li key={index}>
            {alert.city}: {alert.message}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TriggeredAlerts;
