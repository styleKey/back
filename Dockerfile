FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} stylekey.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","/stylekey.jar"]