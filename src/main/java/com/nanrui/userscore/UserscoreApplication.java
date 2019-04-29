package com.nanrui.userscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication()
@ComponentScan("com.nanrui.userscore")
public class UserscoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserscoreApplication.class, args);
    }

}
