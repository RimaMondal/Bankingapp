FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} bankingapp.jar

ENTRYPOINT  ["java","-jar","/bankingapp.jar"]

EXPOSE 8080