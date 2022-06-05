FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/user-service
COPY ${JAR_FILE} /opt/user-service/app.jar
ENTRYPOINT ["java","-jar","/opt/user-service/app.jar"]