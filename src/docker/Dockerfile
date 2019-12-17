FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_gateway-0.0.1.jar /eureka_gateway.jar
ENTRYPOINT ["java","-jar","/eureka_gateway.jar"]
EXPOSE 8999