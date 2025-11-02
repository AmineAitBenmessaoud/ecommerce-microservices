# Multi-stage build for smaller image size
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY product-service/pom.xml .
COPY product-service/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "product-service.jar"]