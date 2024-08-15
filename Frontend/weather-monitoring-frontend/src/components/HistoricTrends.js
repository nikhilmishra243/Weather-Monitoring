// src/components/HistoricTrends.js
import React, { useEffect, useState } from 'react';
import { getHistoricTrends } from '../services/weatherService';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

const HistoricTrends = ({ city }) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await getHistoricTrends(city);
      setData(result.data);
    };
    fetchData();
  }, [city]);

  return (
    <div>
      <h2>Historic Trends for {city}</h2>
      <LineChart width={600} height={300} data={data}>
        <XAxis dataKey="date" />
        <YAxis />
        <CartesianGrid stroke="#eee" strokeDasharray="5 5" />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="temperature" stroke="#8884d8" />
      </LineChart>
    </div>
  );
};

export default HistoricTrends;
