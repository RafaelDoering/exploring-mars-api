# Exploring Mars API

## Running
- Install Java 11 on your computer.
- Install dependencies with `mvnw install`.
- Start the server with `mvnw spring-boot:run`.
- Server will be running on port 8080
## Running with docker
- Install docker latest version.
- Build the docker image with `docker build  -t exploring-mars-api .`
- Run container with `docker run -p 8080:8080 exploring-mars-api`
- Server will be running on port 8080

# Swagger
Swagger is accessible on `/swagger`

## Main libraries
- Server
  - spring
- Database
  - postgresql
- Database communication
  - jpa
- Tests
  - junit
- API Docs
  - openapi

## Architecture
- Controllers: Handle HTTP requests, execute necessary **services** and send a response back to the client.
- DTOs: A.k.a. data transfer objects.
- Exceptions: Application exceptions.
- Models: Database models.
- Repository: Abstract database methods.
- Services: Execute the application business logic, making operations, calling **repositories** and making API's calls.
- Utils: Utility classes.