package com.cognizant.spring_learn_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearn1Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearn1Application.class);

    public static void main(String[] args) {
        LOGGER.debug("START");
        displayCountry();
        LOGGER.debug("END");
    }

    public static void displayCountry() {
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());
    }
}
