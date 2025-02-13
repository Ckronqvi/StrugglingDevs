# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven project files and build
COPY pom.xml . 
COPY src ./src
COPY database.db . 

RUN mvn clean package -DskipTests

# Stage 2: Run the application
# Use OpenJDK 23 image for Spring Boot
FROM openjdk:23-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/target/app.jar . 
COPY database.db .

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]
