
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/java-project-0.0.1-SNAPSHOT.jar /app/java-project-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "java-project-0.0.1-SNAPSHOT.jar"]