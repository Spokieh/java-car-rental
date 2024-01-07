FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/target/*.jar
COPY ./target/beadando-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


# docker build -t spring-beadando .
# docker run -d -p 8080:8080 spring-beadando