# ResumeAnalyzerAI

## 🚀 Overview

ResumeAnalyzerAI is an AI-powered Resume Analysis platform built using **Spring Boot**, **Spring AI**, **Core Java**, **REST APIs**, **Spring Security**, **JWT Authentication**, **Apache Kafka**, **Redis Cache**, and **MySQL**.

The application helps job seekers optimize their resumes by leveraging AI-based analysis, keyword matching, skill extraction, ATS (Applicant Tracking System) scoring, and personalized improvement suggestions.

---

## 📌 Features

### Authentication & Authorization

* User Registration and Login
* JWT-based Authentication
* Role-Based Access Control (RBAC)
* Spring Security Integration
* Secure API Access

### Resume Management

* Upload Resume (PDF/DOCX)
* Resume Parsing
* Resume Storage
* Resume History Tracking

### AI-Powered Resume Analysis

* ATS Score Generation
* Skill Gap Analysis
* Resume Summary Evaluation
* Job Description Matching
* AI-Based Improvement Suggestions
* Keyword Optimization Recommendations

### Caching

* Redis Cache Integration
* Frequently Accessed Data Caching
* Improved API Performance

### Event-Driven Architecture

* Kafka Producer & Consumer
* Resume Processing Events
* Analysis Notification Events
* Asynchronous Processing

### REST APIs

* User APIs
* Authentication APIs
* Resume APIs
* AI Analysis APIs
* Admin APIs

### Database Management

* MySQL Database
* JPA/Hibernate ORM
* Database Migration Support

---

## 🏗️ Tech Stack

| Technology      | Purpose                           |
| --------------- | --------------------------------- |
| Core Java       | Business Logic                    |
| Spring Boot     | Backend Framework                 |
| Spring AI       | AI Integration                    |
| Spring Security | Authentication & Authorization    |
| JWT             | Secure Token-Based Authentication |
| Spring Data JPA | Database Operations               |
| MySQL           | Relational Database               |
| Apache Kafka    | Event Streaming                   |
| Redis Cache     | Performance Optimization          |
| Maven           | Dependency Management             |
| Lombok          | Boilerplate Code Reduction        |
| Swagger/OpenAPI | API Documentation                 |
| Docker          | Containerization                  |

---

## 📂 Project Structure

```text
ResumeAnalyzerAI
│
├── src/main/java
│   ├── config
│   │   ├── SecurityConfig
│   │   ├── JwtConfig
│   │   ├── KafkaConfig
│   │   ├── RedisConfig
│   │   └── OpenAIConfig
│   │
│   ├── controller
│   │   ├── AuthController
│   │   ├── UserController
│   │   ├── ResumeController
│   │   └── AnalysisController
│   │
│   ├── service
│   │   ├── AuthService
│   │   ├── ResumeService
│   │   ├── AIAnalysisService
│   │   └── UserService
│   │
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── mapper
│   ├── exception
│   ├── kafka
│   │   ├── producer
│   │   └── consumer
│   ├── security
│   │   ├── JwtFilter
│   │   ├── JwtUtil
│   │   └── CustomUserDetailsService
│   └── ResumeAnalyzerAiApplication
│
├── src/main/resources
│   ├── application.yml
│   └── schema.sql
│
└── pom.xml
```

---

## 🔐 Authentication Flow

1. User registers.
2. Password is encrypted using BCrypt.
3. User logs in.
4. JWT token is generated.
5. Token is validated for every secured request.
6. Spring Security handles authorization based on user roles.

```text
Client
   │
   ▼
Login API
   │
   ▼
JWT Token Generated
   │
   ▼
Authorization Header
   │
   ▼
JWT Filter Validation
   │
   ▼
Protected Resources
```

---

## ⚡ Kafka Workflow

```text
Resume Upload
      │
      ▼
Kafka Producer
      │
      ▼
resume-analysis-topic
      │
      ▼
Kafka Consumer
      │
      ▼
AI Resume Analysis
      │
      ▼
Store Result in MySQL
```

---

## 🧠 AI Analysis Flow

```text
Resume Upload
      │
      ▼
Resume Parsing
      │
      ▼
Spring AI
      │
      ▼
LLM Processing
      │
      ▼
ATS Score
Skill Analysis
Keyword Suggestions
Job Matching
      │
      ▼
Store Analysis Result
```

---

## 🗄️ Database Design

### User Table

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| name       | VARCHAR   |
| email      | VARCHAR   |
| password   | VARCHAR   |
| role       | VARCHAR   |
| created_at | TIMESTAMP |

### Resume Table

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| user_id     | BIGINT    |
| file_name   | VARCHAR   |
| uploaded_at | TIMESTAMP |

### Analysis Table

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| resume_id   | BIGINT    |
| ats_score   | DOUBLE    |
| suggestions | TEXT      |
| created_at  | TIMESTAMP |

---

## 🔥 API Endpoints

### Authentication APIs

| Method | Endpoint           | Description   |
| ------ | ------------------ | ------------- |
| POST   | /api/auth/register | Register User |
| POST   | /api/auth/login    | Login User    |

### User APIs

| Method | Endpoint           |
| ------ | ------------------ |
| GET    | /api/users/profile |
| PUT    | /api/users/profile |

### Resume APIs

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | /api/resumes/upload |
| GET    | /api/resumes/{id}   |
| GET    | /api/resumes        |

### Analysis APIs

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | /api/analysis/start |
| GET    | /api/analysis/{id}  |

---

## ⚙️ Configuration

### MySQL

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resume_analyzer_db
spring.datasource.username=root
spring.datasource.password=password
```

### Redis

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### Kafka

```properties
spring.kafka.bootstrap-servers=localhost:9092
```

### JWT

```properties
jwt.secret=your-secret-key
jwt.expiration=86400000
```

### Spring AI

```properties
spring.ai.openai.api-key=YOUR_OPENAI_API_KEY
```

---

## 🧪 Running the Project

### Clone Repository

```bash
git clone https://github.com/yourusername/ResumeAnalyzerAI.git
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application runs at:

```text
http://localhost:8080
```

---

## 📖 Swagger Documentation

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🎯 Future Enhancements

* Multi-LLM Support
* Resume Version Management
* Job Portal Integration
* Email Notifications
* Interview Question Generator
* AI Cover Letter Generator
* Analytics Dashboard
* Docker & Kubernetes Deployment
* CI/CD Pipeline Integration

---

## 👨‍💻 Learning Outcomes

This project demonstrates:

* Core Java Fundamentals
* OOP Principles
* Spring Boot Development
* Spring Security
* JWT Authentication
* REST API Design
* Microservice Concepts
* Kafka Event Streaming
* Redis Caching
* MySQL Database Design
* AI Integration with Spring AI
* Production-Grade Backend Development

---

## 📜 License

This project is licensed under the MIT License.

---

## ⭐ Author

**Your Name**

If you found this project useful, please consider giving it a ⭐ on GitHub.
