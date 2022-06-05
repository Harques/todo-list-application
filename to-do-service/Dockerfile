FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/to-do-service
COPY ${JAR_FILE} /opt/to-do-service/app.jar
ENTRYPOINT ["java","-jar","/opt/to-do-service/app.jar"]