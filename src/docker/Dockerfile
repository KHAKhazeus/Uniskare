FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_order-1.0.0.jar /eureka_order.jar
ENTRYPOINT ["java","-jar","/eureka_order.jar"]
EXPOSE 8909
EXPOSE 8910
EXPOSE 8911