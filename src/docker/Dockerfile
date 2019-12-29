FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD eureka_comm-1.0.0.jar /eureka_comm.jar
ENTRYPOINT ["java","-jar","/eureka_comm.jar"]
EXPOSE 8925