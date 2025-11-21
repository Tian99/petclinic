# Step 1 — Build stage with Gradle caching
FROM gradle:8-jdk17 AS builder
WORKDIR /app

# Only copy build scripts first → better cache
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (cached)
RUN ./gradlew dependencies --no-daemon

# Now copy the source code
COPY src ./src

# Build
RUN ./gradlew clean bootJar -x test --no-daemon

# Step 2 — Runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
