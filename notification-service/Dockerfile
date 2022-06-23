FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/notification-service
COPY ${JAR_FILE} /opt/notification-service/app.jar
ENTRYPOINT ["java","-jar","/opt/notification-service/app.jar"]