# Expense Tracker API

A Spring Boot REST API for managing personal expenses and budgets. The application uses PostgreSQL for persistence and Spring Security for protected API routes.

## Features

- User registration with BCrypt password hashing
- Create, read, update, and delete expenses
- Create, read, update, and delete budgets
- Spending totals by category
- Spending totals over time
- Change or delete the current user account
- Bean validation and centralized error handling

## Technology

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven Wrapper

## Requirements

- JDK 17 or newer
- PostgreSQL
- Docker, only if you want to use the container image

## Configuration

The application reads the PostgreSQL connection details from environment variables:

```text
POSTGRES_URI=jdbc:postgresql://localhost:5432/expense_tracker
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=your-password
```

Create the database before starting the application:

```sql
CREATE DATABASE expense_tracker;
```

Hibernate is configured with `ddl-auto: update`, so the schema is updated from the entity classes when the application starts. SQL logging is enabled in the default configuration.

## Run Locally

Set the required environment variables, then start the application with the Maven Wrapper:

```powershell
$env:POSTGRES_URI = "jdbc:postgresql://localhost:5432/expense_tracker"
$env:POSTGRES_USERNAME = "postgres"
$env:POSTGRES_PASSWORD = "your-password"
.\mvnw.cmd spring-boot:run
```

The API is available at `http://localhost:8080/api`.

To run the tests:

```powershell
.\mvnw.cmd test
```

On macOS or Linux, use `./mvnw` instead of `.\mvnw.cmd`.

## Docker

Build the application jar first, then build and run the image:

```powershell
.\mvnw.cmd clean package
docker build -t expense-tracker-api .
docker run --rm -p 8080:8080 `
  -e POSTGRES_URI="jdbc:postgresql://host.docker.internal:5432/expense_tracker" `
  -e POSTGRES_USERNAME="postgres" `
  -e POSTGRES_PASSWORD="your-password" `
  expense-tracker-api
```

The container exposes port `8080` and expects the packaged jar in `target/`.

## API Overview

All routes below are relative to `http://localhost:8080/api`.

### Authentication

| Method | Path | Description | Auth |
| --- | --- | --- | --- |
| `POST` | `/auth/register` | Register a user | Public |

Example registration request:

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

The password must contain at least 8 characters. All routes other than `/auth/**` require authentication. A login or token-issuing endpoint is not currently implemented in the API.

### Expenses

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/v1/expenses` | List expenses with Spring pagination parameters |
| `GET` | `/v1/expenses/{id}` | Get an expense |
| `POST` | `/v1/expenses` | Create an expense |
| `PUT` | `/v1/expenses/{id}` | Update an expense |
| `DELETE` | `/v1/expenses/{id}` | Delete an expense |

Example expense request:

```json
{
  "description": "Weekly groceries",
  "category": "FOOD",
  "amount": 54.75,
  "date": "2026-10-06"
}
```

### Budgets

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/v1/budgets` | List budgets with Spring pagination parameters |
| `GET` | `/v1/budgets/{id}` | Get a budget |
| `POST` | `/v1/budgets` | Create a budget |
| `PUT` | `/v1/budgets/{id}` | Update a budget |
| `DELETE` | `/v1/budgets/{id}` | Delete a budget |

Example budget request:

```json
{
  "description": "October food budget",
  "category": "FOOD",
  "amount": 400.00,
  "startDate": "2026-10-01",
  "endDate": "2026-10-31"
}
```

### Dashboard

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/v1/dashboard/spending-by-category?category=FOOD` | Get spending for a category |
| `GET` | `/v1/dashboard/spending-over-time` | Get spending totals over time |

### User Account

| Method | Path | Description |
| --- | --- | --- |
| `PUT` | `/v1/users/changePassword` | Change the current user's password |
| `DELETE` | `/v1/users/me` | Delete the current user's account |

## Expense Categories

Valid category values are:

`FOOD`, `TRANSPORT`, `ENTERTAINMENT`, `RENT`, `UTILITIES`, `SHOPPING`, `HEALTH`, `OTHER`

## Project Layout

```text
src/main/java/.../expense_tracker/
├── auth/          Registration and security configuration
├── budget/        Budget entity, API, and persistence logic
├── category/      Expense category enum
├── dashboard/     Spending summaries
├── expense/       Expense entity, API, and persistence logic
├── exception/     Error responses and global exception handling
└── user/          User account management
```

## License

No license has been specified for this project yet.