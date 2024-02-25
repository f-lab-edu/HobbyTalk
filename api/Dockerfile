FROM openjdk:17-jdk-slim as builder

COPY build/libs/api-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-Dspring.profiles.active=production", "-jar", "app.jar"]