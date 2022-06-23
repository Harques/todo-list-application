FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/auth-service
COPY ${JAR_FILE} /opt/auth-service/app.jar
ENTRYPOINT ["java","-jar","/opt/auth-service/app.jar"]