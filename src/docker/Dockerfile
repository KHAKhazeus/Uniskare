FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_order-0.01.jar /eureka_user.jar
ENTRYPOINT ["java","-jar","/eureka_user.jar"]
EXPOSE 8909,8910,8911