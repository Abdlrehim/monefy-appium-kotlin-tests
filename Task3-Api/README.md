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

