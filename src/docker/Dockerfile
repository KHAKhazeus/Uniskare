FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_user-0.0.1.jar /eureka_user.jar
ENTRYPOINT ["java","-jar","/eureka_user.jar"]
EXPOSE 8909
EXPOSE 8910
EXPOSE 8911