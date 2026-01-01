# Java 8 (official, supported)
FROM eclipse-temurin:8-jdk

LABEL maintainer="govardhan.reddy@example.com"

COPY target/bookstore-spring-boot-demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
