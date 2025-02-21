FROM maven:3.9.9 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install
FROM openjdk:17-jdk-slim

COPY --from=build /app/target/loteria-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]