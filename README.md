# ReqRes API Test Automation Framework

## Overview

The test task involves creating a test automation framework to cover the following scenarios for the REST API [ReqRes](https://reqres.in/):

1. List Users
2. Single User
3. Create User
4. Login Successful
5. Login Unsuccessful

The following technologies are utilized:
- Java
- RestAssured
- Maven
- TestNG/JUnit
- Jackson
- Allure

Later, I added a JSON schema validator dependency as I felt that some of the responses should be validated by schema.

## Project Structure

The project is organized into the following packages:

## Main/Model

I've created Java class representing the `User` model.

## Test/BaseTest

Then I've created the `BaseTest` class in the test package to set up common configurations and to initialize `baseURI` there.

## Test/Tests

I decided to divide the tests into two different classes: 

- `LoginTest` 
- `UsersTest`

## Test/Resources/JsonSchemas

JSON schema for validating the single user API response:

- `usersList` 
- `singleUser`

# 
