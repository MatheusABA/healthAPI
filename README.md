# Clinical Appointment Management System Backend API 🏥
This backend system is designed to manage doctor and patient information, schedule appointments, and handle authentication. It is built using Spring Boot, JPA/Hibernate for data persistence, and PostgreSQL as the database. The API is secured using JWT authentication.

## Technologies Used
- Backend Framework: Spring Boot
- Database: PostgreSQL
- ORM: JPA, Hibernate
- Authentication: JWT

## API Endpoints *(prefix = /api/v1)*
### Authentication Routes
- POST /auth/login\
*Authenticates a user and returns a JWT token.*

**Body**:
```
{
  "username": "user@example.com",
  "password": "password123"
}
```
**Response**:
```
{
  "token": "your_token_here"
}
```

- POST /auth/register\
*Register a new account.*

**Body**:
```
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "john123"
}
```
**Response**:
```
{
  "message": "User registered successfully"
}
```

### Doctor Management Routes *(require JWT token)*
- POST /doctors\
*Adds a new doctor to the system.*

**Body**:
```
{
  "name": "John Doe",
  "specialty": "Cardiology",
  "crm": "Doctor_Code",
  "email": "doctor@example.com",
  "phone": "+1234567890",
}
```
**Response**:
```
  "id": 2
  "name": "John Doe",
  "specialty": "Cardiology",
  "crm": "Doctor_Code",
  "email": "doctor@example.com",
  "phone": "+1234567890",
  "createdAt": Date
}
```


- GET /doctors\
*Fetches a list of all doctors in the system.*

**Response**:
```
[
  {
    "id": 1,
    "name": "John Doe",
    "specialty": "Cardiology",
    "crm": "19238-MT",
    "email": "doctor@example.com",
    "phone": "+1234567890",
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "specialty": "Neurology",
    "crm": "28D9S-DF",
    "email": "jane.smith@example.com",
    "phone": "+0987654321",
  }
]

```

- DELETE /doctors/{id}\
*Deletes a doctor from the system by ID.*

**Response**:
```
{
  "message": "Doctor deleted successfully"
}
```

### Patient Management Routes *(require JWT token)*
- POST /patients\
*Adds a new patient to the system.*

**Body**:
```
{
  "name": "John Doe",
  "email": "doctor@example.com",
  "phone": "+1234567890",
  "age": 23,
  "address": "John Avenue",
  "medicalHistory": "Notes about patient"
}
```
**Response**:
```
{
  "id": 1,
  "name": "John Doe",
  "email": "doctor@example.com",
  "phone": "+1234567890",
  "age": 23,
  "address": "John Avenue",
  "medicalHistory": "Notes about patient"
}
```
- GET /patients\
*Fetches a list of all patients in the system.*

**Response**:
```
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "doctor@example.com",
    "phone": "+1234567890",
    "age": 23,
    "address": "John Avenue",
    "medicalHistory": "Notes about patient"
  },
  {
    "id": 2,
    "name": "John Doe",
    "email": "doctor@example.com",
    "phone": "+1234567890",
    "age": 23,
    "address": "John Avenue",
    "medicalHistory": "Notes about patient"
  }
]
```
- DELETE /patients/{id}\
*Deletes a patient from the system by ID.*

**Response**:
```
{
  "message": "Patient deleted successfully"
}
```

### Schedules Management Routes *(require JWT token)*
- POST /schedules\
*Schedules a new schedules for a patient with a doctor.*

**Body**:
```
  "doctor": doctor_id,
  "patient": patient_id,
  "date": LocalDate,
  "time"  Timestamp,
  "description": "Medical Notes",
  "status": pending
```
**Response**:
```
  "id": 1,
  "doctor": Doctor_Name,
  "patient": Patient_name,
  "date": LocalDate,
  "time"  Timestamp,
  "description": "Medical Notes",
  "status": pending
```

- GET /schedules/today\
*Retrieves all schedules for today.*

**Response**:
```
[
  {
    "id": 1,
    "doctor": {
      "name": "John Doe",
      "specialty": "Cardiology"
    },
    "patient": {
      "name": "Alice Brown",
      "medicalHistory": "Patient notes"
    },
    "date": "2025-03-01T10:00:00",
    "time",
    "description": "Initial check-up"
  }
]
```

- DELETE /schedules/{id}\
*Deletes an schedule by ID.*

**Response**:
```
{
  "message": "Schedule deleted successfully"
}
```

## Running the backend
### Prerequisites
- Java 17 or higher
- PostgreSQL
- Maven

## Steps to run
Clone this repository:
```
git clone https://github.com/MatheusABA/healthAPI.git
```
Navigate to directory:
```
cd healthapi
```
Configure the application.properties to set up the project:
```
spring.application.name=healthapi

# PORT
server.port=8080
# DATABASE
spring.datasource.url=jdbc:postgresql://localhost/${spring.datasource.name}?useSSL=false
spring.datasource.name=DB_Name
spring.datasource.username=postgres
spring.datasource.password=DB_Password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
#JWT
security.jwt.secret=Insert_Secret_Here
security.jwt.issuer=Main
security.jwt.ttlMillis=604800000
```
Run the app:
```
mvn spring-boot:run
```
The backend API will now be running at http://localhost:8080 by default.
