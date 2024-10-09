# ReqRes API Test Automation Framework

## Overview

The test task involves creating a test automation framework to cover the following scenarios for the REST API [ReqRes](https://reqres.in/): using RestAssured and responce validation.

## Requirements

- Java 8+
- Maven
- TestNG
- RestAssured

## Dependencies

All required dependencies are managed in the `pom.xml` file.

### Key Libraries
- **Rest Assured** for testing RESTful APIs
- **TestNG** for testing
- **Jackson Databind** for converting Java objects to and from JSON
- **JSON Schema Validator** for validating JSON responses against a JSON Schema

## Setup Instructions

1. Import the project:
    ```
    git clone https://github.com/ziniann/reqResAPI.git
    ```
    ```
    cd reqResAPI
    ```
2. Build the project using Maven:
    ```
    mvn clean install
    ```
3. Run the test suite:
    ```
    mvn test
    ```
