FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:15c47cd825f2bf77b40860bc9c18d4659c72584d16ef5f533eb49a232b3702f3
WORKDIR /app
RUN addgroup --system javauser && adduser -S javauser -G javauser
USER javauser:javauser
COPY build/libs/connect-people-0.1.jar connect-people.jar
ENTRYPOINT ["java","-jar", "/app/connect-people.jar"]