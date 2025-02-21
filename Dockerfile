FROM openjdk:8-jdk-alpine

COPY ./target/loteria-0.0.1-SNAPSHOT.jar loteria.jar

ENTRYPOINT ["java", "-jar", "loteria.jar"]