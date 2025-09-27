# Etapa 1: build
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Etapa 2: run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/app/build/libs/*.jar app-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-1.0-SNAPSHOT.jar"]
