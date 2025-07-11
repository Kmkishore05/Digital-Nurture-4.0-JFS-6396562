package com.cognizant.spring_learn_3.controller;

import com.cognizant.spring_learn_3.model.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
    @RequestMapping("/country")
    public Country getCountryIndia() {
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Country country = (Country) context.getBean("country");
        return country;
    }
}
