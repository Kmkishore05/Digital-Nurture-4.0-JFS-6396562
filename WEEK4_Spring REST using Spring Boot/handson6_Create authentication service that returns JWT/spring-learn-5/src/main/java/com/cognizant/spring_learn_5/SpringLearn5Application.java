package com.cognizant.spring_learn_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
//✅ Disable default Spring Security user
@EnableMethodSecurity(prePostEnabled = true)
public class SpringLearn5Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringLearn5Application.class, args);
    }
}
