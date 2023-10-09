package com.jai.phonebookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.jai")
@EnableReactiveMongoRepositories(basePackages = "com.jai.core.infrastructure.out.persistence.mongodb.repository")
public class PhonebookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApiApplication.class, args);
    }

}
