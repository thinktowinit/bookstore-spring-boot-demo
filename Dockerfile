# Java 8 (official, supported)
#FROM eclipse-temurin:8-jdk
#
#LABEL maintainer="govardhan.reddy@example.com"
#
#COPY target/bookstore-spring-boot-demo-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#Above is for single staged, below for multi staged f

# ===== Stage 1: Build Stage =====
FROM maven:3.9.3-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2: Runtime Stage =====
FROM eclipse-temurin:8-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]



