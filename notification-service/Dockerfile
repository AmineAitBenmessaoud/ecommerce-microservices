# Multi-stage build for smaller image size
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "notification-service.jar"]