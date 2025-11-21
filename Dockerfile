# ==========================================================
# Step 1 — Gradle build stage with full caching
# ==========================================================
FROM gradle:8-jdk17 AS builder
WORKDIR /app

# Copy only Gradle config first (for cache)
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle

# Pre-download dependencies (takes advantage of Docker layer cache)
RUN ./gradlew --no-daemon dependencies || true

# Copy application source
COPY src ./src

# Build application Jar
RUN ./gradlew bootJar -x test --no-daemon

# ==========================================================
# Step 2 — Runtime image
# ==========================================================
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built Jar
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
