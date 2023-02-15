package com.agilethought.schedulerpml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Main Class for start spring Boot Application
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SchedulerPmlApplication {
	/**
	 * Main method to start Spring Boot Application
	 * @param args standard arguments for Spring
	 */
	public static void main(String[] args) {
		SpringApplication.run(SchedulerPmlApplication.class, args);
	}
	/**
	 * Method to create Bean 
	 * @return
	 */
	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}
}
