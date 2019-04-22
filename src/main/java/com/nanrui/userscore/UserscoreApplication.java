package com.nanrui.userscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
public class UserscoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserscoreApplication.class, args);
    }

}
