Weather Monitoring System - Backend

This repository contains the backend code for the Weather Monitoring System, built with Spring Boot. The backend provides RESTful APIs to fetch weather data, store weather summaries, and trigger alerts.

Table of Contents

Project Overview

Prerequisites

Installation

Configuration

Running the Application

API Endpoints

Design Choices

Dependencies

Project Overview

The Weather Monitoring System collects weather data from multiple cities using the OpenWeatherMap API, processes the data, stores it in a database, and triggers alerts based on specific conditions. The system also maintains a daily weather summary for each city, recording the minimum, maximum, and average temperatures.

Prerequisites

Ensure you have the following installed on your system:

Java 17 or higher

Maven (for building the project)

Docker (for running the database)

Git (for version control)

Installation

Clone the Repository

git clone https://github.com/yourusername/weather-monitoring-backend.git

cd weather-monitoring-backend

Set Up the Database

The application uses MySQL as the database. You can run MySQL using Docker:

docker run --name weather-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=weatherdb -p 3306:3306 -d mysql:latest

Build the Project

Navigate to the project root and run:

mvn clean install

Configuration

The application is configured using application.properties. You need to configure the following properties:


properties

spring.datasource.url=jdbc:mysql://localhost:3306/weatherdb

spring.datasource.username=root

spring.datasource.password=root

weather.api.key=YOUR_OPENWEATHERMAP_API_KEY

weather.api.url=http://api.openweathermap.org/data/2.5/weather

Running the Application
You can run the application using Maven or directly through the command line:

mvn spring-boot:run

Dependencies

The backend project depends on several libraries and frameworks:

Spring Boot: Core framework for the application.

Spring Data JPA: For database interactions.

Spring Web: For building RESTful APIs.

MySQL Connector: For connecting to the MySQL database.

Lombok: To reduce boilerplate code.

Docker: To run the MySQL database container.