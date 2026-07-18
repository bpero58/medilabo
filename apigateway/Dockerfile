# ---- Build Stage ----
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy Gradle wrapper and build files first (better layer caching)
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Make wrapper executable (important — permissions can get lost depending on your OS/git config)
RUN chmod +x ./gradlew

# Pre-download dependencies as a separate cached layer
RUN ./gradlew dependencies --no-daemon || true

# Now copy source and build
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# ---- Runtime Stage ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
