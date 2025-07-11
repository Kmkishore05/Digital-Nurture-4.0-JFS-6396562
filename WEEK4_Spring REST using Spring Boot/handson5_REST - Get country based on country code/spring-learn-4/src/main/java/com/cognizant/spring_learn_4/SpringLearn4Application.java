package com.cognizant.spring_learn_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:country.xml")
public class SpringLearn4Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringLearn4Application.class, args);
    }
}
