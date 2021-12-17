package com.epam.esm.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.epam.esm"})
@EnableJpaRepositories(basePackages = {"com.epam.esm.repository"})
@EntityScan(basePackages = {"com.epam.esm.entity"})
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

}