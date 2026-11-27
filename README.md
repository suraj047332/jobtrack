# JobTrack — Job Application Management System

A Java full-stack portfolio project built with Spring Boot, Spring Security, Spring Data JPA, Thymeleaf, HTML, CSS, JavaScript-ready UI, and MySQL.

## Features
- Public landing page and responsive UI
- User registration with validation and BCrypt password hashing
- Spring Security form login, logout, authenticated routes, and session handling
- Personal dashboard with live application statistics and recent activity
- Create, read, update, and delete job applications
- Search by job title/company and filter by application status
- Track applied and interview dates, job links, and notes
- Profile editing and authenticated password change
- User-owned records: a user can only view or change their own applications
- Authenticated JSON endpoint: `GET /api/dashboard/stats`

## Requirements
- Java 17 or later
- Maven 3.9+
- MySQL 8+
- IntelliJ IDEA, Eclipse, or VS Code with Java extensions

## 1. Create the database
Start MySQL and run:

```sql
CREATE DATABASE jobtrack;
```

The application also uses `createDatabaseIfNotExist=true` in its default JDBC URL. Ensure the MySQL account has permission to create databases, or create it manually.

## 2. Configure database credentials
Open `src/main/resources/application.properties`. The defaults are:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtrack?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

Change username/password to match your local MySQL installation. Alternatively set environment variables `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

## 3. Run the application
From the project root:

```bash
mvn spring-boot:run
```

Or import `pom.xml` as a Maven project in IntelliJ/Eclipse and run `com.jobtrack.JobTrackApplication`.

Open **http://localhost:8080**.

## 4. Use the application
1. Select **Get started** and create your own account.
2. Sign in with that email and password.
3. Add a job application from the dashboard.
4. Change its status as you progress through the hiring process.
5. Use search/filter, application details, profile, and password settings.

There is no hard-coded demo account. Register your own account; passwords are stored as BCrypt hashes.

## Main routes
| Route | Purpose | Access |
|---|---|---|
| `/` | Landing page | Public |
| `/register` | Create account | Public |
| `/login` | Sign in | Public |
| `/dashboard` | Dashboard | Authenticated |
| `/applications` | List/search/filter applications | Authenticated |
| `/applications/new` | Create application | Authenticated |
| `/applications/{id}` | Application details | Owner only |
| `/applications/{id}/edit` | Edit application | Owner only |
| `/profile` | Profile and password | Authenticated |
| `/api/dashboard/stats` | Dashboard stats JSON | Authenticated |

## Database model
- `users`: account identity, email, BCrypt password hash, role, creation time
- `job_applications`: job/company details, status, dates, notes, and owning user foreign key

Hibernate creates/updates the tables on startup with `ddl-auto=update`. For a production deployment, replace this with versioned Flyway/Liquibase migrations and configure secrets outside source control.

## Project structure
```text
src/main/java/com/jobtrack
  config/       Security configuration
  controller/   MVC and REST controllers
  model/        JPA entities and enums
  repository/   Spring Data repositories
  service/      Business logic and UserDetailsService
src/main/resources
  templates/    Thymeleaf pages and fragments
  static/css/   Responsive stylesheet
```

## Portfolio talking points
- Spring Security authenticates users and protects application routes.
- BCrypt hashes passwords; raw passwords are never persisted.
- Spring Data JPA maps Java entities to MySQL tables.
- Service classes hold business rules; controllers handle HTTP requests and responses.
- Repository methods scope application queries by authenticated user ID to prevent cross-account access.
- Thymeleaf renders server-side pages; the stats endpoint demonstrates a protected REST API.

## Notes
- This is a local-development portfolio project, not a production-hardened SaaS. Before deployment, add HTTPS, secure cookie settings, rate limiting, email verification, automated database migrations, backups, and integration tests.
- The Google Fonts import is optional; the UI falls back to system fonts when offline.
