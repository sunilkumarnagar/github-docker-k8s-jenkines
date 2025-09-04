FROM openjdk:17
COPY target/dockertest-0.0.1-SNAPSHOT.jar  /app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app.jar"]
