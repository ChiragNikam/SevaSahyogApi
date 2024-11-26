# SevaSahyog – Empowering NGOs Through Digital Solutions

SevaSahyog is a modern platform designed to simplify NGO operations by providing a digital ecosystem to manage accounts, events, and authentication securely. Built with Spring Boot and leveraging state-of-the-art technologies, this application ensures a smooth and efficient user experience while enabling NGOs to focus on their mission.

---

## Table of Contents

1. [Features](#features)
2. [Technology Stack](#technology-stack)
3. [Installation and Setup](#installation-and-setup)
4. [API Endpoints](#api-endpoints)
5. [Usage](#usage)
6. [Future Enhancements](#future-enhancements)

---

## Features

- **NGO Account Management:**
    - Create, update, and manage NGO profiles.
    - Upload and manage profile and background images.

- **Event Management:**
    - Create, update, and delete events with detailed tracking.
    - Filter past and upcoming events by year or date.
    - Upload and manage event-specific images.

- **Secure Authentication System:**
    - JWT-based authentication for sign-up and sign-in.
    - Robust token management with error handling.

- **Centralized Error Handling:**
    - Global exception handling for consistent responses.

- **Scalable and Modular Design:**
    - Ready for future enhancements and easy integration with external systems.

---

## Technology Stack

- **Backend:** Spring Boot
- **Authentication:** Spring Security with JWT
- **Database:** Relational Database (e.g., MySQL/PostgreSQL)
- **File Storage:** Firebase Storage (for profile and event images)
- **Build Tool:** Maven

---

## Installation and Setup

### Prerequisites:

1. **Java 17** or above
2. **Maven**
3. **Docker** (optional, for containerized deployment)
4. **MySQL/PostgreSQL database**
5. **Firebase account** for file storage configuration

### Steps to Set Up Locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/sevasahyog.git
   cd sevasahyog
2. **Configure the database in application.properties or application.yml:**
    ``` bash
    spring.datasource.url=jdbc:mysql://localhost:3306/sevasahyog
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
3. **Configure Firebase Storage:**
   - Add your Firebase SDK JSON file to the project.
   - Update the Firebase integration configuration.
4. **Build the project:**
   ``` bash
   mvn clean install
5. **Run the application:**
   ```bash
   mvn spring-boot:run

---

## API Endpoints

**Authentication (`/auth/ngo`)**
- POST /signIn: Sign in with email and password.
- POST /signUp: Register a new NGO account.

**NGO Account Management (`/account/ngo`)**
- `GET /{userId}`: Retrieve NGO account details by user ID.
- `PUT /{userId}`: Update NGO account details.
- `PUT /updateImage/{userId}`: Update profile and background images.

**Event Management (`/ngo/events`)**
- `GET /all`: Retrieve all events.
- `GET /user/{id}`: Retrieve events for a specific user.
- `GET /user/{id}/upcoming`: Retrieve upcoming events for a user.
- `GET /user/{id}/pastEventYears`: Retrieve past event years for a user.
- `POST`: Create a new event.
- `PUT`: Update an existing event.
- `PUT /{eventId}/eventImages`: Update event images.
- `DELETE /{id}`: Delete an event.

---

## Usage

- NGOs can securely sign up, log in, and manage their profiles.
- Easily track, create, and update events, ensuring stakeholders remain informed.
- Administrators can use centralized error handling to debug and resolve issues effectively.

---

## Future Enhancements

1. **Donor Management:**
   - Add features to connect NGOs with potential donors.
2. **Volunteer Coordination Module:**
   - Automate volunteer signups and event participation tracking.
3. **Payment Gateway Integration:**
   - Allow NGOs to accept donations through integrated payment solutions.
---

**Built with ❤️ by the SevaSahyog Team. Together, let’s empower NGOs to create a greater impact!**