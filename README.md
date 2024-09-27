# Starbucks Fake

This is a Java-based backend application simulating a Starbucks service for managing drinks, orders, and user interactions. The project is designed to demonstrate the use of Spring Boot, PostgreSQL and other technologies to handle typical coffee shop functionalities such as adding and categorizing drinks, managing orders, and handling user accounts.

## Table of Contents
- [Description](#description)
- [Technologies](#technologies)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)

## Description

The **Starbucks Fake** project mimics the core functionalities of a Starbucks application, allowing users to create, view, and manage drinks and orders. It includes features like:
- User registration and authentication
- Adding and categorizing drinks
- Order management
- Integration with PostgreSQL for database storage

You can access the live application deployed on Heroku at:

**[Starbucks Fake on Heroku](https://starbucks-fake-a8d6ed640a8f.herokuapp.com/)**
  
**[Swagger UI](https://starbucks-fake-a8d6ed640a8f.herokuapp.com/swagger-ui/index.html#)**
## Technologies

The project is built with the following technologies:
- **Java** (JDK 11)
- **Spring Boot 2.5.7** for backend
- **PostgreSQL** as the database
- **Docker** for containerization of the database
- **MapStruct** for object mapping
- **JWT (Json Web Token)** for security and authentication
- **Swagger** for API testing and documentation

## Requirements

Before you begin, ensure you have the following software installed on your machine:
- **Java JDK 11**
- **Maven or Gradle** (for dependency management)
- **Docker** (for running PostgreSQL container)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/IlyasNasirov/Starbucks_fake.git
   cd Starbucks_fake
2. Set up the database using Docker:
   ```bash
   docker-compose up -d
3. Install the dependencies:
   ```bash
   mvn clean install
4. Start the application:
   ```bash
   mvn spring-boot:run
## Usage

The application provides an API to manage users, drinks, and orders. Once the application is running, you can access the API through Swagger UI at:
**[Swagger UI](https://localhost:8080/swagger-ui/index.html#)**

## Example API Usage:

To get the list of available drinks:
   ```bash
  curl -X GET http://localhost:8080/api/v1/drinks
