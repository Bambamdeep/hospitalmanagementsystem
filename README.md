# 🏥 Hospital Management System — Microservices Architecture

A distributed Hospital Management System built using **Java 17**, **Spring Boot 3.x**, and **microservices architecture**. This project demonstrates enterprise-grade design patterns including service discovery,
inter-service communication, role-based security, and asynchronous messaging.


## 📦 Microservices Overview

         Service                                               Description 

| **Patient Service**                          | Manages patient information |
| **Doctor Service**                           | Manages doctor information  |
| **Appointment Service**                      | Manages appointments between patients and doctors |
| **Department Service**                       | Manages hospital departments |
| **Billing Service**                          | Handles patient billing payments |
| **Eureka**                                   | Service Discovery to register the services to find Each |
| **hospitalgateway**                          | Single EntryPoint for all the service to access |
| **hospitalconfigserver**                     | Centralized Configuration Management for all Microservices |

## 🚀 Tech Stack

- **Java 17**, **Spring Boot 3.x**
- **Spring Data JPA** with **H2 Database**
- **Spring Security** with Role-Based Access Control (`ADMIN`,`USER`)
- **Spring Cloud OpenFeign** for inter-service REST communication
- **RabbitMQ** for asynchronous messaging (notifications)
- **Spring Cloud Gateway** for API routing
- **Spring Cloud Eureka** for service discovery
- **Spring Cloud Config** for centralized configuration
- **Swagger / OpenAPI** for API documentation
- **JUnit 5**, **Mockito** for testing

## 🔐 Security

- Role-based access control using **Spring Security**
- Roles: ADMIN, USER

## Default Credentials for Testing

|  Role  | Username      | Password   |
| ADMIN | deepak         | 1234  |
| USER  | kumar          | 123   |


## 📚 API Documentation

Each microservice exposes its own Swagger UI:

       Service                                                               Swagger URL 

| Patient Service                                                  |  http://localhost:8081/docs |
| Doctor Service                                                   |  http://localhost:8082/docs |
| Appointment Service                                              |  http://localhost:8083/docs |
| Department Service                                               |  http://localhost:8084/docs |
| Billing Service                                                  |  http://localhost:8085/docs |

---
## 🌐 Service Endpoints

           Service                       Port          Base Endpoint                        Through Gateway                                               
                                           
|  **Patient Service**              | 8081     | http://localhost:8081/patient      | http://localhost:8762/patient |
|  **Doctor Service**               | 8082     | http://localhost:8082/doctor       | http://localhost:8762/doctor  |
|  **Appointment Service**          | 8083     | http://localhost:8083/appointment  | http://localhost:8762/appointments |
|  **Department Service**           | 8084     | http://localhost:8084/department   | http://localhost:8762/departments |
|  **Billing Service**              | 8085     | http://localhost:8085/bill         | http://localhost:8762/bills|
|  **Config Server**                | 8888     | http://localhost:8888              ||
|  **Eureka Server**                | 8761     | http://localhost:8761              ||
|  **API Gateway**                  | 8762     | http://localhost:8080              ||


##  API Endpoints  

###  Patient Service  
**Base URL:** http://localhost:8081/patient  
- POST /create → Create a new patient  
- GET /id/{patientId} → Retrieve a patient by ID  
- GET /all → Retrieve all patients  
- PUT /update/{patientId} → Update an existing patient  
- DELETE /delete/{patientId} → Delete a patient  
- GET /name/search?name={name} → Search patients by name   

---

###  Doctor Service  
**Base URL:** http://localhost:8082/doctor  
- POST /create → Create a new doctor  
- GET /id/{doctorId} → Retrieve a doctor by ID  
- GET /all → Retrieve all doctors  
- PUT /update/{doctorId} → Update an existing doctor  
- DELETE /delete/{doctorId} → Delete a doctor  
- GET /specialization/{specialization} → Get doctors by specialization  
- GET /did/{departmentId} → Get doctors in a department  
  

---

###  Appointment Service  
**Base URL:** http://localhost:8083/appointment  
- POST /create → Create a new appointment  
- GET /id/{appointmentId} → Retrieve an appointment by ID  
- GET /all → Retrieve all appointments  
- PUT /update/{appointmentId} → Update an existing appointment  
- DELETE /delete/{appointmentId} → Delete an appointment  
- GET /pid/{patientId} → Retrieve appointments for a patient  
- GET /did/{doctorId} → Retrieve appointments for a doctor  
 

---

###  Department Service  
**Base URL:** http://localhost:8084/department  
- POST /create → Create a new department  
- GET /id/{departmentId} → Retrieve a department by ID  
- GET /all → Retrieve all departments  
- PUT /update/{departmentId} → Update an existing department  
- DELETE /delete/{departmentId} → Delete a department  
 

---

###  Bill   
**Base URL:** http://localhost:8085/bill  
- POST /create → Create a new bill  
- GET /id/{billingId} → Retrieve a bill by ID  
- GET /all → Retrieve all bills  
- PUT /update/{billingId} → Update an existing bill  
- DELETE /delete/{billingId} → Delete a bill  
- GET /pid/{patientId} → Retrieve bills for a patient  
- GET /aid/{appointmentId} → Retrieve bills for appointment  



##  Inter-Service Communication

- **Appointment Service** uses **OpenFeign** to fetch patient and doctor details from respective services .
- Service URLs are managed via application.properties or Eureka.

---

##  RabbitMQ Integration

- RabbitMQ is used for **event-driven notifications**.
- Example: When an appointment is created, a message is published to a queue.
- Consumers (e.g., Notification Service) listen and send alerts or receipts.


## ⚙️ How to Run the Project  

### 🧩 Prerequisites  
Ensure the following are installed:  
- **Java 17**  
- **Maven 3.8+**  
- **IDE** (IntelliJ / Eclipse recommended. Import it in the Ide)  








