# Step 1 — Build stage with Gradle caching
FROM gradle:8-jdk17 AS builder
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN ./gradlew build -x test --no-daemon || true

COPY src ./src

RUN ./gradlew bootJar -x test --no-daemon

# Production image
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

# Copy entrypoint
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080

# Use entrypoint.sh as single ENTRYPOINT
ENTRYPOINT ["/entrypoint.sh"]
