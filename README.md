# E-commerce Microservices Application

This project is a microservices-based e-commerce platform built using **Spring Boot** and various other tools and technologies for security, tracing, messaging, and databases.

# Architecture Overview

This application follows the Microservices Architecture where each service handles a specific domain in the e-commerce system such as product catalog, orders, payments, etc. Each service is independently deployable and communicates via REST APIs and messaging.

# Microservices Components
- **Customer Service:** Manages user profiles and authentication.
- **Order Service:** Handles order management and order tracking.
- **Product Catalog Service:** Manages product listings and inventory.
- **Payment Service:** Handles payment processing.
- **Notification Service:** Sends notifications (email/SMS) to customers.

# Tools and Technologies
- **Spring Boot:** Core framework for building microservices.
- **Keycloak:** Provides identity and access management (IAM) to secure the microservices.
- **Zipkin:** Used for distributed tracing and tracking requests across microservices.
- **Kafka:** Messaging system for inter-service communication.
- **MongoDB:** NoSQL database used for services like user profiles and product catalog.
- **PostgreSQL:** Relational database for transactional services like orders and payments.

# Prerequisites
- Java 17 or higher
- Maven
- ocker (for setting up Kafka, MongoDB, PostgreSQL, and other infrastructure)
- Keycloak server (can be set up using Docker)
