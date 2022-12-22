FROM eclipse-temurin:17-jdk-alpine as build-stage
WORKDIR /app

RUN addgroup --system javauser && adduser -S -s /usr/sbin/nologin -G javauser javauser
USER users:javauser

COPY gradlew .
COPY .gradle .gradle
COPY build.gradle .
COPY src src

RUN ./gradlew build -DskipTests
RUN mkdir build/dependency
RUN cd build/dependency
RUN jar xf ../libs/connect-people-0.1-a.jar

FROM eclipse-temurin:17.0.5_8-jre-alpine@sha256:15c47cd825f2bf77b40860bc9c18d4659c72584d16ef5f533eb49a232b3702f3

ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.connectpeople.ConnectPeopleApplication"]