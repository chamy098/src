#!/bin/bash

# Build your Spring Boot projects using Gradle or Maven
cd actor-service
./gradlew :application:bootJar
cd ..

cd movie-service
./gradlew :application:bootJar
cd ..

# Start Docker Compose
docker-compose up -d