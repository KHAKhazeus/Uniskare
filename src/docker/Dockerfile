FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_server-0.01.jar /eureka_server.jar
ENTRYPOINT ["java","-jar","/eureka_server.jar"]
EXPOSE 8991