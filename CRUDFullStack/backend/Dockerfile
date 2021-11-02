# Build
FROM maven:3.8-openjdk-8-slim AS build
COPY src /src
COPY pom.xml .
RUN mvn -Dmaven.test.skip clean package

# Deploy
FROM openjdk:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
