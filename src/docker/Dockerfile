FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_server-1.0.0.jar /eureka_server.jar
ENTRYPOINT ["java","-jar","/eureka_server.jar"]
EXPOSE 8991