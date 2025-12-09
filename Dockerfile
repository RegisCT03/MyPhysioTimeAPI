FROM gradle:8-jdk17 AS build
WORKDIR /app

COPY gradle ./gradle

COPY build.gradle.kts settings.gradle.kts gradle.properties ./

COPY src ./src

RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*-all.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]