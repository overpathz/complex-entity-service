
![ERD](https://github.com/user-attachments/assets/f9806b15-1c62-4f30-8449-f103a4e9c40b)

# Complex Entity Relationship Service

A **Spring Boot** application demonstrating **Hibernate** (via Spring Data JPA) with **MapStruct** and **Lombok**, plus a simple **React**-based frontend. The project models a scenario where **Employees**, **Departments**, **Projects**, and **User Profiles** have the following relationships:

- **Department** to **Employee**: One-to-Many
- **Employee** to **UserProfile**: One-to-One
- **Employee** to **Project**: Many-to-Many

This repository includes:
1. **Spring Boot Backend** with REST endpoints.
2. **Database Schema** showcasing complex relationships.
3. **DTOs** + **MapStruct** mappers to separate domain models from API layers.
4. A **React** admin panel to create and manage data.

---

## Features

- **Entity Relations**:
    - **One-to-One**: Each `Employee` can have one `UserProfile`.
    - **One-to-Many**: A `Department` can have multiple `Employees`.
    - **Many-to-Many**: Employees can be assigned to multiple `Projects` (and vice versa).

- **MapStruct** for DTO ↔ Entity conversion, ensuring clean separation between internal models and external API payloads.

- **CRUD Operations** via **Spring Data JPA** repositories:
    - Create, Read, Update, Delete employees, departments, projects, and user profiles.

- **React Admin Panel**:
    - Demonstrates a minimal frontend where you can list, create, and delete entities.
    - Uses **Axios** to interact with the backend REST endpoints.

---

## Tech Stack

- **Backend**
    - **Spring Boot** (Web + Data JPA)
    - **Hibernate** (via Spring Data JPA)
    - **Lombok** (for boilerplate-free data classes)
    - **MapStruct** (compile-time DTO mapping)
    - **PostgreSQL**

- **Frontend**
    - **React** + **Axios**
    - **Create React App** structure (optional dev proxy at `localhost:3000`)

---

## Architecture Overview

1. **Entities** (in `entity` package):
    - `Employee`, `Department`, `Project`, `UserProfile`
2. **DTOs** (in `dto` package):
    - **Plain objects** representing how data is exchanged over REST.
3. **Mappers** (in `mapper` package):
    - **MapStruct** interfaces to convert between DTOs and entities.
4. **Repositories** (in `repository` package):
    - Extend `JpaRepository` to leverage Spring Data’s built-in CRUD operations.
5. **Services** (in `service` package):
    - Contain **business logic** and orchestrate repository calls, also annotated with `@Transactional`.
6. **Controllers** (in `controller` package):
    - **REST endpoints** exposing CRUD operations in JSON.

---

## Database Schema

Below is a concise **DBML** definition of the schema. You can use [dbdiagram.io](https://dbdiagram.io) to visualize it:

```dbml
// Use DBML to define your database structure
// Docs: https://dbml.dbdiagram.io/docs

Table department {
  id BIGINT [pk, increment]
  name VARCHAR
}

Table user_profile {
  id BIGINT [pk, increment]
  address VARCHAR
  phone_number VARCHAR
  emergency_contact VARCHAR
}

Table employee {
  id BIGINT [pk, increment]
  name VARCHAR
  email VARCHAR
  user_profile_id BIGINT
  department_id BIGINT
}

Table project {
  id BIGINT [pk, increment]
  name VARCHAR
}

Table employee_project {
  employee_id BIGINT
  project_id BIGINT

  indexes {
    (employee_id, project_id) [pk]
  }
}

Ref: employee.user_profile_id > user_profile.id [update: cascade, delete: set null, note: "One-to-One"]
Ref: employee.department_id > department.id [update: cascade, delete: set null, note: "Many-to-One"]
Ref: employee_project.employee_id > employee.id [delete: cascade]
Ref: employee_project.project_id > project.id [delete: cascade]
```

---

## Quick Start

```
1. **Clone the Repository**  
   git clone https://github.com/your-user/complex-entity-service.git
   cd complex-entity-service

2. **Backend** (Spring Boot):
   # Make sure you have Java 17+ or a compatible version installed.
   # Then run (Maven example):
   ./mvnw spring-boot:run

   By default, the app will start on http://localhost:8080.

3. **Frontend** (React):
   cd frontend
   npm install
   npm start

   This will launch the development server at http://localhost:3000.
   - If you want a proxy, add "proxy": "http://localhost:8080" in your package.json.
   - Otherwise, ensure your CORS settings allow calls from 3000.

4. **Interact with the Service**
   - Employees: /api/employees
   - Departments: /api/departments
   - Projects: /api/projects

   Or use the React Admin Panel at http://localhost:3000 to create and manage entities via a simple UI.
```

---

## Usage

- **Employee Endpoints**
    - `POST /api/employees` – Create new Employee
    - `GET /api/employees` – List all Employees
    - `GET /api/employees/{id}` – Get an Employee by ID
    - `PUT /api/employees/{id}` – Update an Employee
    - `DELETE /api/employees/{id}` – Delete an Employee

- **Department Endpoints**
    - `POST /api/departments` – Create a Department
    - `GET /api/departments/{id}` – Get a Department by ID
    - `PUT /api/departments/{id}` – Update a Department
    - `DELETE /api/departments/{id}` – Delete a Department
    - `GET /api/departments/{id}/count` – Count Employees in Department

- **Project Endpoints**
    - `POST /api/projects` – Create a Project
    - `GET /api/projects` – List all Projects
    - `GET /api/projects/{id}` – Get a Project by ID
    - `PUT /api/projects/{id}` – Update a Project
    - `DELETE /api/projects/{id}` – Delete a Project

Open the **React Admin Panel** in your browser at `http://localhost:3000` to create or manage entities via a simple UI.  
(If you configured a proxy, requests to `/api/...` will automatically be forwarded to your Spring Boot backend.)

---
