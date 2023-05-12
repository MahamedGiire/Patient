FROM openjdk:17
EXPOSE 8080
COPY target/patient-service-docker.jar patient-service-docker.jar
ENTRYPOINT ["java", "-jar", "/patient-service-docker.jar"]