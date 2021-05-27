FROM adoptopenjdk/openjdk11:alpine-jre

COPY build/libs/*.jar /kysokApp/kysokApp.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar","/kysokApp/kysokApp.jar"]