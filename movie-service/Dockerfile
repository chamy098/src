FROM openjdk:17-oracle

ARG JAR_FILE=application/build/libs/movie-service.jar

WORKDIR /opt/app

COPY ${JAR_FILE} movie-service.jar

CMD ["java","-jar","movie-service.jar"]
