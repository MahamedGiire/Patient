FROM openjdk:17
EXPOSE 9090
COPY target/patient-service-docker.jar patient-service-docker.jar
ENTRYPOINT ["java", "-jar", "/patient-service-docker.jar"]