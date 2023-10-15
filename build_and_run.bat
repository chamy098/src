cd actor-service && gradlew :application:bootJar && cd .. && cd movie-service && gradlew :application:bootJar && cd .. && docker-compose up -d
