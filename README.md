# Spring Boot 3 Multi-Module Project with API-First and Hexagonal Architecture

Welcome to the Spring Boot 3 Multi-Module Project! This project showcases best practices for building a Spring Boot
application with a focus on API-First design and Hexagonal architecture, all using Maven as the build tool. This project
serves as both a template and reference for creating scalable and maintainable Java applications.

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Modules](#modules)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Building and Running](#building-and-running)

## Project Overview

The project aims to create a robust and modular application using Spring Boot 3, adhering to API-First and Hexagonal
architecture principles. Here's a brief overview of the key features and concepts:

### Architecture

#### API-First Design

API-First design encourages defining your API contracts (RESTful APIs) before implementing the application logic. This
approach promotes clear communication and collaboration between frontend and backend teams.

#### Hexagonal Architecture

Hexagonal Architecture (also known as Ports and Adapters) separates the application into core business logic and
external dependencies. This design enables easier testing, maintainability, and adaptability to changes.

### Modules

The project is organized into several modules to maintain separation of concerns and facilitate code reusability:

1. **core**: Houses the core business logic and domain entities and the persistence repositories all the common code
   that can be reused by the other modules.

## Getting Started

To get started with this project, follow these steps:

1. Clone this repository to your local machine:

   ```
   git clone https://github.com/jugurta/phonebook.git
   ```

2. Navigate to the project directory:

   ```
   cd phonebook
   ```

3. Build the project using Maven:

   ```
   mvn clean install
   ```

4. Start the application:

   ```
   cd web
   mvn spring-boot:run
   ```

Now, your Spring Boot 3 Multi-Module Project is up and running locally.

## API Documentation

You can access the API documentation for this project by navigating to:

```
http://localhost:8080/swagger-ui.html
```

This provides a comprehensive guide to the available API endpoints, request/response schemas, and examples.

## Building and Running

To build the project, run the following Maven command:

```
mvn clean install
```

To run the application, use the following Maven command within the `phonebook--api` module:

```
docker compose up
cd phonebook--api
mvn spring-boot:run
```

By default, the application will start on port 8080. You can modify the port in the `application.yml` file.