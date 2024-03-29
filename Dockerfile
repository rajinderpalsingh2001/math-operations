FROM openjdk:17
EXPOSE 8090
ADD target/mathoperations-docker.jar mathoperations-docker.jar
ENTRYPOINT ["java","-jar","/mathoperations-docker.jar"]