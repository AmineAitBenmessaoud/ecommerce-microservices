# E-Commerce Microservices Platform

A scalable, event-driven e-commerce system built with **Spring Boot microservices architecture**, demonstrating modern distributed system design patterns and cloud-native development practices.

## Project Overview

This project showcases a production-grade microservices implementation for an e-commerce platform. It emphasizes **loose coupling**, **asynchronous communication**, and **independent service scalability** - key principles for building resilient distributed systems.

### Key Highlights
- ✅ **4 Independent Microservices** with domain-driven design
- ✅ **Event-Driven Architecture** using Apache Kafka for inter-service communication
- ✅ **Containerized Deployment** with Docker & Docker Compose
- ✅ **Database per Service** pattern for data autonomy
- ✅ **RESTful APIs** for client communication
- ✅ **Async Messaging** for decoupled service integration

---

##  Architecture

### System Architecture Diagram
```
┌─────────────┐
│   Clients   │
└──────┬──────┘
       │
       ├──────────────┬──────────────┬──────────────┐
       │              │              │              │
       ▼              ▼              ▼              ▼
┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐
│ Product  │   │  Order   │   │ Payment  │   │Notification│
│ Service  │   │ Service  │   │ Service  │   │  Service   │
│  :8081   │   │  :8082   │   │  :8083   │   │   :8084    │
└─────┬────┘   └─────┬────┘   └─────┬────┘   └─────┬──────┘
      │              │              │              │
      └──────────────┴──────────────┴──────────────┘
                          │
                          ▼
                  ┌───────────────┐
                  │ Apache Kafka  │
                  │   Zookeeper   │
                  └───────┬───────┘
                          │
                  ┌───────▼───────┐
                  │  PostgreSQL   │
                  │   (Shared)    │
                  └───────────────┘
```

### Microservices

| Service | Port | Responsibility | Tech Stack |
|---------|------|----------------|------------|
| **Product Service** | 8081 | Product catalog management, inventory | Spring Boot, JPA, PostgreSQL |
| **Order Service** | 8082 | Order creation, order lifecycle management | Spring Boot, JPA, Kafka Producer |
| **Payment Service** | 8083 | Payment processing, transaction handling | Spring Boot, JPA, Kafka Consumer/Producer |
| **Notification Service** | 8084 | Email/SMS notifications, event logging | Spring Boot, JPA, Kafka Consumer |

---

##  Technology Stack

### Backend
- **Framework:** Spring Boot 3.5.7
- **Language:** Java 17
- **Build Tool:** Maven
- **ORM:** Spring Data JPA / Hibernate

### Database
- **RDBMS:** PostgreSQL 15 (with separate schemas per service)
- **Pattern:** Database per Service

### Messaging & Events
- **Message Broker:** Apache Kafka
- **Coordination:** Apache Zookeeper
- **Pattern:** Event-Driven Architecture

### DevOps & Deployment
- **Containerization:** Docker
- **Orchestration:** Docker Compose
- **Configuration:** Environment Variables (.env)

### Additional Tools
- **Lombok:** Reduces boilerplate code
- **Spring Boot DevTools:** Hot reload for development

---

## Features Implemented

### 1. **Microservices Communication**
- **Synchronous:** RESTful APIs for client-facing operations
- **Asynchronous:** Kafka event streaming for inter-service communication

### 2. **Event-Driven Workflow**
Example flow:
1. Customer places order → **Order Service** creates order
2. **Order Service** publishes `OrderCreatedEvent` → Kafka
3. **Payment Service** consumes event → processes payment
4. **Payment Service** publishes `PaymentCompletedEvent` → Kafka
5. **Notification Service** consumes event → sends confirmation email

### 3. **Data Management**
- Each service maintains its own database schema
- Database initialization scripts for automated setup
- JPA/Hibernate for object-relational mapping

### 4. **Containerization**
- Dockerfiles for each microservice
- Multi-container orchestration with Docker Compose
- Health checks and service dependencies configured
- Volume management for data persistence

### 5. **Configuration Management**
- Externalized configuration using environment variables
- Service discovery through Docker networking
- Configurable database connections and Kafka brokers

---

## Project Structure

```
ecommerce-microservices/
├── product-service/          # Product catalog microservice
│   ├── src/main/java/        # Business logic
│   ├── pom.xml               # Maven dependencies
│   └── Dockerfile            # Container definition
├── order-service/            # Order management microservice
├── payment-service/          # Payment processing microservice
├── notification-service/     # Notification handler microservice
├── docker-compose.yml        # Multi-container orchestration
├── init-db.sh               # Database initialization script
└── README.md                # Project documentation
```

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker & Docker Compose
- PostgreSQL (for local development)

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone https://github.com/AmineAitBenmessaoud/ecommerce-microservices.git
   cd ecommerce-microservices
   ```

2. **Configure environment variables**
   Create a `.env` file:
   ```env
   POSTGRES_DB=ecommerce
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   POSTGRES_PORT=5432
   
   KAFKA_PORT=9092
   ZOOKEEPER_PORT=2181
   ZOOKEEPER_CONNECT=zookeeper:2181
   KAFKA_BOOTSTRAP_SERVERS=kafka:9092
   
   PRODUCT_SERVICE_PORT=8081
   ORDER_SERVICE_PORT=8082
   PAYMENT_SERVICE_PORT=8083
   NOTIFICATION_SERVICE_PORT=8084
   
   PRODUCT_DB_URL=jdbc:postgresql://postgres-db:5432/productdb
   ORDER_DB_URL=jdbc:postgresql://postgres-db:5432/orderdb
   PAYMENT_DB_URL=jdbc:postgresql://postgres-db:5432/paymentdb
   NOTIFICATION_DB_URL=jdbc:postgresql://postgres-db:5432/notificationdb
   ```

3. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

4. **Access the services**
   - Product Service: http://localhost:8081
   - Order Service: http://localhost:8082
   - Payment Service: http://localhost:8083
   - Notification Service: http://localhost:8084

---

##  Skills Demonstrated

This project showcases proficiency in:

-  **Microservices Architecture** - Designing distributed systems with independent, scalable services
-  **Event-Driven Design** - Implementing asynchronous communication patterns
-  **Spring Boot Ecosystem** - Leveraging Spring Boot, Spring Data JPA, Spring Kafka
-  **Message Brokers** - Working with Apache Kafka for reliable event streaming
-  **Database Design** - Implementing database-per-service pattern
-  **Containerization** - Dockerizing applications and multi-container orchestration
-  **RESTful API Development** - Building HTTP APIs following REST principles
-  **DevOps Practices** - Using Docker Compose for local development environments
-  **Configuration Management** - Externalizing configs with environment variables
-  **Domain-Driven Design** - Organizing code around business domains

---

##  Future Enhancements

- [ ] **API Gateway** - Centralized entry point with Spring Cloud Gateway
- [ ] **Service Discovery** - Implement Eureka or Consul for dynamic service registration
- [ ] **Circuit Breaker** - Add Resilience4j for fault tolerance
- [ ] **Distributed Tracing** - Integrate Sleuth/Zipkin for request tracking
- [ ] **Authentication & Authorization** - Implement OAuth2/JWT with Spring Security
- [ ] **Monitoring** - Add Prometheus + Grafana for metrics visualization
- [ ] **CI/CD Pipeline** - GitHub Actions for automated testing and deployment
- [ ] **Kubernetes Deployment** - Migrate from Docker Compose to Kubernetes

---

##  License

This project is created for educational and portfolio purposes.

---

##  Author

**Amine Ait Benmessaoud**  
Junior Software Engineer  
[GitHub Profile](https://github.com/AmineAitBenmessaoud)

---

##  Contact

For questions or feedback about this project, please open an issue or reach out via GitHub.

---

**Built with ❤️ to demonstrate modern microservices architecture**
