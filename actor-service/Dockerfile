FROM openjdk:17-oracle

ARG JAR_FILE=application/build/libs/actor-service.jar

WORKDIR /opt/app

COPY ${JAR_FILE} actor-service.jar

CMD ["java","-jar","actor-service.jar"]
