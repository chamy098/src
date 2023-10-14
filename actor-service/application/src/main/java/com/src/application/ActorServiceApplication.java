package com.src.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.src.datamodel"})
@EnableJpaRepositories(basePackages = {"com.src.persistence"})
@ComponentScan(basePackages = {"com.src.application", "com.src.core", "com.src.datamodel", "com.src.persistence", "com.src.service"})
@EnableCaching
public class ActorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorServiceApplication.class, args);
    }

}
