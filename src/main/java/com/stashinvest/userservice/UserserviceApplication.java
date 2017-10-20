package com.stashinvest.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Main class for User Service spring boot app.
 * 
 * @author abhimanyu
 *
 */
@SpringBootApplication
@EnableScheduling
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	    return new MethodValidationPostProcessor();
	}
}
