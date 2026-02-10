# Examify – Microservices-Based Online Exam Platform

Examify is a scalable online competitive exam platform designed using
Spring Boot microservices architecture.

## Architecture
- API Gateway (Spring Cloud Gateway)
- Auth Service (JWT Authentication)
- Exam Service (Exams & Questionnaires)
- Submission Service (Evaluation)
- Leaderboard Service (Redis)
- Config Server (Central Config)
- Eureka Server (Service Discovery)
- Kafka (Async leaderboard updates)

## Tech Stack
- Java 17
- Spring Boot
- Spring Cloud (Gateway, Eureka, Config)
- Redis
- Kafka
- PostgreSQL
- Docker
- AWS

## Key Features
- JWT-based authentication
- Random questionnaire allocation
- No questionnaire repetition
- Real-time leaderboard using Redis
- Event-driven architecture using Kafka
- Rate limiting & circuit breaker at API Gateway

## How to Run Locally
```bash
docker-compose up --build
