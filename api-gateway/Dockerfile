FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/api-gateway
COPY ${JAR_FILE} /opt/api-gateway/app.jar
ENTRYPOINT ["java","-jar","/opt/api-gateway/app.jar"]