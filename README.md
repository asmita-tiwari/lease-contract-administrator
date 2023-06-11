# Allane SE  - REST API for Leasing Contract Administrator

## Introduction

This Spring boot application contains an implementation of a leasing application that allows for the administration of leasing contracts.

Sure! Here's an updated version of the features and endpoints, including the "Deleted" and "Update" functionalities:

## Features

* **Create a Contract:** The API allows administrators to create a new leasing contract by providing the following relevant details:
    - Contract Number
    - Monthly Rate

  Additionally, the leasing contract is connected to one customer, who can have several leasing contracts. The customer is defined by the following properties:
    - First name
    - Last name
    - Birthdate

  Furthermore, the leasing contract is associated with one specific vehicle, and a vehicle can only be connected to one contract at a time. The vehicle is characterized by the following properties:
    - Brand
    - Model
    - Model year
    - Vehicle identification number (VIN) - This field can be empty during contract creation
    - Price

* **Update a Contract:** The API allows administrators to update an existing leasing contract with new information, such as updating the monthly rate, customer details, or vehicle information.
* **Get a Contract:** The API allows administrators to retrieve information about a specific leasing contract by its unique identifier.


## Endpoints

* POST /contract - Allows the creation of a new leasing contract.

* PUT /contract - Updates an existing leasing contract identified by the {contractId} with new information.
* 
* GET /contract/{contractId} - Retrieves information about the leasing contract identified by the {contractId}.

## Requirements
* Java 11
* Mysql
* Gradle
* Docker Desktop

## Technologies Used
* Spring Boot
* Java
* Gradle
* RESTful API
* Swagger UI (API Documentation)
* Docker
* Flyway database migration

## Instructions to Build and Run
To run the project follow the instructions below:

1. Clone the Git repository containing the project to your local machine.

   ```bash
   git clone https://github.com/asmita-tiwari/lease-contract-administrator.git
   ```

2. Navigate to the root directory of the project.

   ```bash
   cd [project_directory]
   ```
3. Run the following command to start the containers defined in the docker-compose.yml
    ```bash
   docker-compose up
   ```
4. Build the project using the Gradle wrapper. Run the following command in the project's root directory:

   ```bash
   ./gradlew build
   ```

   This command will download the required Gradle version and build the project. If you are using Windows, use `gradlew.bat` instead of `./gradlew`.

5. Once the build process is complete, you can run the project using the Gradle wrapper with the following command:

   ```bash
   ./gradlew bootRun
   ```

   This command will start the application using the embedded Tomcat server.

6. Access the application in your web browser by navigating to
   ```
   http://localhost:8080/swagger-ui/index.html
   ```
Note: Credentials are available in application.properties
## Testing and Coverage
The project includes a comprehensive set of unit tests and integration tests to ensure the correctness and reliability of the implemented functionality. Test coverage reports are generated to track the percentage of code covered by the tests. To run the tests, execute the following command:

## Code Quality
To ensure code quality, the Checkstyle tool has been used in this project. Checkstyle enforces a set of coding conventions and rules to maintain a consistent coding style and identify potential issues or violations.


## Design Patterns
The project incorporates design patterns to address known issues and improve maintainability. Some of the design patterns used include:
- **Builder Pattern**: Used for creating complex objects with multiple fields.
- **Repository Pattern**: Implemented to separate data access logic from the business logic.
- **Exception Handling**: Custom exceptions and centralized exception handling are used to provide meaningful error responses and maintain a consistent error-handling approach.

## Known Issues/ Future Tasks

The project has been developed with a focus on production readiness. However, the following known issues or limitations should be considered:

### 1. Delete Endpoint for Managing Deletion of Contracts

Currently, the project lacks the implementation Delete endpoints for deleting leasing contracts. Implementing these endpoints will enhance the functionality of the application and provide more comprehensive contract management capabilities.

### 2. GitHub Actions for Deployment
To streamline the deployment process, GitHub Actions will be implemented as part of the CI/CD pipeline. GitHub Actions provide an automated workflow to build, test, and deploy the application. The actions will be configured to trigger on specific events, such as pushing to the repository's main branch or creating a new release. This will help ensure consistent and reliable deployments, reducing manual effort and increasing development efficiency.
Furthermore, it is essential to ensure the security of our credentials. To accomplish this, we will securely store the necessary credentials by utilizing GitHub Secrets. These secrets provide a safe and encrypted storage solution, mitigating the risk of unauthorized access to sensitive information.

### 3. Logging and Monitoring Dashboard
Logging and Monitoring Dashboard to track and analyze system logs, application logs, performance metrics, and other relevant data.

## Conclusion
This README provides an overview of the Leasing Contract Administrator Platform REST API project for managing Leasing Contracts. It outlines the project structure, technologies used, build instructions, testing approach, design patterns employed, and known issues. Please refer to the relevant sections for more details on each aspect of the project.