FROM eclipse-temurin:26-jre

WORKDIR /app

COPY target/*.jar app.jar

#Add ENV

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

