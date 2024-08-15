Weather Monitoring System - Frontend
This repository contains the frontend code for the Weather Monitoring System, built with React.js. The frontend provides a dashboard to display weather summaries, historical trends, and triggered alerts.

Table of Contents
Project Overview
Prerequisites
Installation
Running the Application
Directory Structure
Design Choices
Dependencies

Project Overview
The frontend of the Weather Monitoring System displays real-time weather summaries, historical data trends, and triggered alerts for multiple cities. It fetches data from the backend via RESTful APIs and provides a user-friendly interface to visualize the data.

Prerequisites
Ensure you have the following installed on your system:

Node.js (v14 or higher)
npm or yarn (package managers)
Docker (optional, for running the frontend in a container)
Git (for version control)

Installation
Clone the Repository

git clone https://github.com/nikhilmishra243/weather-monitoring-frontend.git
cd weather-monitoring-frontend
Install Dependencies

Navigate to the project root and install the necessary packages:

npm install
Running the Application
Development Mode

Run the following command to start the development server:

npm start
This will start the application on http://localhost:3000.

Production Build

To create a production build of the application:

npm run build
You can serve the production build using a simple HTTP server like serve:

npx serve -s build
Directory Structure
src/: Contains the source code for the React application.
components/: Reusable components like WeatherSummary, HistoricTrends, and Alerts.
services/: API service files that interact with the backend.
App.js: The main application component.
index.js: Entry point for the application.
public/: Contains static assets like index.html.
package.json: Lists dependencies and scripts for the project.
Design Choices
React.js: Chosen for its component-based architecture and efficient state management.
Axios: Used for making HTTP requests to the backend APIs.
Chart.js: Integrated for rendering historical weather data trends in a visually appealing manner.
Responsive Design: The UI is designed to be responsive, ensuring compatibility across various devices.
Error Handling: Implemented basic error handling for network issues and invalid data.

Dependencies
The frontend project depends on several libraries:

React.js: Core framework for building the user interface.
Axios: For making HTTP requests to the backend.
Chart.js: For rendering data visualizations.
Bootstrap: For styling and responsive design.
