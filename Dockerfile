FROM openjdk:8
ADD target/weatherreport-0.0.1-SNAPSHOT.jar weatherreport-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "weatherreport-0.0.1-SNAPSHOT.jar"]