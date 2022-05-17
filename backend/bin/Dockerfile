FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8089
ADD target/multiwebsites-1.0.jar multiwebsites-1.0.jar
ENTRYPOINT ["java","-jar","/multiwebsites-1.0.jar"]