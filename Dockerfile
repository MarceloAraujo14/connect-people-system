FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:15c47cd825f2bf77b40860bc9c18d4659c72584d16ef5f533eb49a232b3702f3 as build

RUN addgroup --system javauser && adduser -S javauser -G javauser
USER root

COPY --chown=javauser:javauser gradlew /gradlew
COPY --chown=javauser:javauser gradle /gradle
COPY --chown=javauser:javauser build.gradle .
COPY --chown=javauser:javauser settings.gradle .

COPY src .src

RUN ./gradlew bootJar

FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:15c47cd825f2bf77b40860bc9c18d4659c72584d16ef5f533eb49a232b3702f3

COPY --from=build build/libs/connect-people-0.1.jar connect-people.jar

RUN chmod 775 .

USER javauser

ENTRYPOINT ["java","-jar", "/app/connect-people.jar"]