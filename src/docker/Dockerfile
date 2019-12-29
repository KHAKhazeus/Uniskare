FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_user-1.0.0.jar /eureka_user.jar
ENTRYPOINT ["java","-jar","/eureka_user.jar"]
EXPOSE 8928