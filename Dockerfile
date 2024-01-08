FROM openjdk:17-jdk
COPY ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


# docker build -t beadando:0.0.1 .

# docker run -p 3307:3306 --name mysqlcontainer -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=car_rental -d mysql

# docker network craete networkmysql

# docker network connect networkmysql mysqlcontainer

# docker run -p 8090:8080 --name beadando --net networkmysql -e MYSQL_HOST=mysqlcontainer -e MYSQL_PORT=3306 -e MYSQL_DB_NAME=car_rental -e MYSQL_USER=root -e MYSQL_PASSWORD=pass DOCKER_IMAGE_ID