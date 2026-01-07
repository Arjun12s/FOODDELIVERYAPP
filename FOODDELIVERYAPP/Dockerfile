# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the FULL src directory (correct path)
COPY src ./src

# Build the WAR
RUN mvn clean package -DskipTests

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy WAR from build stage
COPY --from=build /app/target/*.war app.war

EXPOSE 8080
CMD ["java", "-jar", "app.war"]
