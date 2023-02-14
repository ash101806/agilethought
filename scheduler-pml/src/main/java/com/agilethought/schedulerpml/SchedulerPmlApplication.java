package com.agilethought.schedulerpml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
@EnableAsync
public class SchedulerPmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerPmlApplication.class, args);
	}

}
