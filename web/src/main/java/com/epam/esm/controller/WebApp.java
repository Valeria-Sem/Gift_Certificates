package com.epam.esm.controller;

import com.epam.esm.converter.UserConverter;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApp {
//    private UserConverter userConverter;
//    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

    @Bean
    public GiftCertificateService giftCertificateService() {
        return new GiftCertificateServiceImpl();
    }

    @Bean
    public TagService tagService() {
        return new TagServiceImpl();
    }

//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl(userRepository, userConverter);
//    }

}