FROM openjdk:8-jre

COPY rest-api.jar /app/rest-api.jar

EXPOSE 8080

WORKDIR /app

ENTRYPOINT ["java", "-Dspring.profiles.active=prod","-jar", "rest-api.jar"]
