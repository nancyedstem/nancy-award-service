# Award Service Management
# Technology Stack
- Postgres
- Spring Boot 2.7.8
- Java 11
- Junit5
- Swagger
- Docker
- Kubernetes


## Database Setup

This setup is following the postgres setup using docker.

- Run the command ```docker pull postgres```
- Start the docker container for postgres
- ```docker run --name odw-postgres-local -p5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres```




# Running the application
Add an application-local.properties to match the postgres setup

# Docker
Running our project in docker container:
- build an image
  ``` docker build -t nancy-award-service ```
- run a docker container
  ```docker run --name nancy-award-service -p 8080:8080```
# Helm
- create charts:
  ```helm create award-service```
- deploy application :
  ```helm upgrade service-award-release --install helm/nancy-award-service```
- remove deployment:
  ```helm uninstall helm/nancy-award-service```
# Verification
Run the backend using the command
```mvn spring-boot:run -Dspring-boot.run.profiles=local```
in your local machine.

# Swagger url

- http://localhost:8080/swagger-ui/

