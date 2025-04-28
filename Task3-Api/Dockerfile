FROM gradle:8.1.1-jdk17 AS builder
WORKDIR /app
COPY . .

# Build the project (this will download dependencies and prepare Gradle cache)
RUN gradle assemble --no-daemon

# Default CMD: always run tests when the container starts
CMD ["gradle", "test", "--no-daemon"]

#RUN gradle test --no-daemon

# CMD ["sh", "-c", "cp -r build/reports /reports"]

# FROM openjdk:17-slim
# COPY --from=builder /app/build/libs/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

