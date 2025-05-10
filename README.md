# Spring Boot Microservices Workshop

This project is a hands-on implementation of microservices using Spring Boot, based on the [Java Brains YouTube Playlist](https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas).

> ⚠️ **Important:** All microservices in this project are built using **JDK 11**. Make sure your environment is set to JDK 11 to avoid compatibility issues with project dependencies.

---

## 📚 What I Learned

This project helped me understand the concepts of building and communicating between microservices using Spring Boot. I created three microservices:

1. **Movie Info Service** – Provides movie metadata (title, description, etc.)
2. **Ratings Data Service** – Supplies user ratings for movies
3. **Movie Catalog Service** – Aggregates data from the above two services and presents a unified API to users

The idea was to create two independent services and a third that depends on them by merging their results.

---

## 🧱 Architecture Overview

![Microservices Architecture](https://github.com/user-attachments/assets/912bb313-3d11-40ec-962b-367ef131a8a9)

The architecture is composed of three microservices communicating with each other via REST APIs, and later registered and discovered through **Eureka Server** for dynamic service discovery.

---

## 🛠️ Key Concepts Covered

### 1. **Creating Microservices**
Introduced the microservices architecture and benefits of small, independently deployable services communicating via HTTP.

### 2. **Designing the Sample Microservices**
Planned a Movie Catalog system using:
- **Movie Info Service**
- **Ratings Data Service**
- **Movie Catalog Service** (aggregator)

### 3. **Creating Starter Microservice Projects**
Set up base Spring Boot projects with appropriate dependencies and project structure.

### 4. **Building the Movie Catalog Service API**
Built endpoints to call both info and rating services, combine responses, and return a final result.

### 5. **Building the Movie Info Service API**
Created standalone APIs to return movie details based on the movie ID.

### 6. **Configuring Server Ports**
Assigned different ports to each service using `application.properties` to allow them to run concurrently.

### 7. **Using a Bean to Create a RestTemplate Instance**
Defined `RestTemplate` as a Spring Bean to reuse it across service layers for HTTP communication.

### 8. **Using WebClient to Make API Calls**
Implemented `WebClient` as a non-blocking alternative to `RestTemplate` for better scalability in reactive applications.

### 9. **Making a Call to the Ratings API**
Demonstrated how the Catalog Service makes external HTTP calls to fetch user rating data.

### 10. **Why You Should Avoid Returning Lists in APIs**
Learned best practices for API response design: always wrap lists in a container object to allow metadata like paging or status.

### 11. **Using RestTemplate for Inter-Service Calls**
Handled inter-service communication, request building, and exception handling with `RestTemplate`.

---

## 🔍 Service Discovery & Load Balancing

### 12. **Understanding Service Discovery**
Covered the importance of dynamically discovering service instances instead of hard-coding URLs.

### 13. **Introducing Eureka**
Learned how Eureka (by Netflix) helps register and discover microservices in real-time.

### 14. **Starting a Eureka Server**
Set up a Eureka Server with Spring Boot to manage service registration and discovery.

### 15. **Creating Eureka Clients**
Configured each microservice to register itself with the Eureka Server using appropriate dependencies and annotations.

### 16. **Discovering Services Through Eureka**
Demonstrated how services can query the Eureka registry to locate other services without static configurations.

### 17. **Doing Client-Side Load Balancing**
Enabled client-side load balancing using **Spring Cloud LoadBalancer** (previously Ribbon) to distribute requests across service instances.

---

## 🚀 Conclusion

This workshop provided a strong foundation in:
- Microservice architecture
- Inter-service communication (synchronous)
- Service discovery with Eureka
- Client-side load balancing

It also enforced best practices like reusable HTTP clients, proper API design, and modularization.
