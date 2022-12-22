# Stage 1: Build the application
FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:15c47cd825f2bf77b40860bc9c18d4659c72584d16ef5f533eb49a232b3702f3

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/connect-people-0.1-a.jar
COPY ${JAR_FILE} connect-people-0.1-a.jar
ENTRYPOINT ["java","-jar","/connect-people-0.1-a.jar"]