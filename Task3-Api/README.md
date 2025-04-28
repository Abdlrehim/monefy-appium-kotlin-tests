# Petstore RESTful API Test Automation

## Overview
A fully Dockerized Kotlin-based test automation framework designed to test CRUD endpoints of the Petstore API.

The project uses:
- **Ktor Client** for HTTP API communication
- **Kotest** for expressive test writing and assertions
- **Gradle** for build automation
- **Docker + Docker Compose** for isolated, repeatable execution

---

## Setup
- Clone the repository
- Ensure **Docker** and **Docker Compose** are installed on your system

---

## How to Run Tests

1. Build the project and run tests:

```
docker-compose up –build
```

This builds the Docker image and automatically runs all tests.

2. Re-run tests (without rebuilding):

```
docker-compose up
```

## Test Reports

- The **test reports** are located in the following directory:
 ```
Task3-Api/test-report/reports/tests/test/packages/index.html
```

## The test file, which contains the API tests
```
Task3-Api/src/test/kotlin/PetApiTest.kt
```



