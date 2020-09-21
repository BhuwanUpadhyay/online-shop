FROM mcr.microsoft.com/java/jre:15-zulu-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "--enable-preview", "-jar" , "/app.jar"]
