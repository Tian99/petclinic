# Step 1 — Build stage with Gradle 8
FROM gradle:latest AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Step 2 — Runtime stage (ARM + AMD64 compatible)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
