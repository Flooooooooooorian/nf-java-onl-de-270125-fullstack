FROM eclipse-temurin:22

COPY backend/target/app.jar /app.jar

EXPOSE 8080

ENV TZ=Europe/Berlin

LABEL maintainer="florian.weber@neuefische.de"

ENTRYPOINT ["java", "-jar", "/app.jar"]
