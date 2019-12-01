FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java","-jar","/test.jar"]
EXPOSE 8761