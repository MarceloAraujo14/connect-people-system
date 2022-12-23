FROM gradle:jdk17-alpine as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -DskipTests --no-daemon

FROM eclipse-temurin:17.0.5_8-jre-alpine

EXPOSE 80

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/connect-people.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseContainerSupport","-XX:MaxRAMPercentage=80", "-XX:InitialRAMPercentage=40", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/connect-people.jar"]

