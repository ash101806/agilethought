package com.ashleyzapien.kafkapractice;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ashleyzapien.kafkapractice.app.MessageProducer;

@EnableAutoConfiguration
@SpringBootApplication
public class KafkaPracticeApplication implements CommandLineRunner {
	@Autowired
	private MessageProducer producer;
	private static final Logger LOG = LoggerFactory.getLogger(KafkaPracticeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(KafkaPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		while (true) {
			try {
				String line = scanner.nextLine();
				producer.send(line);
			} catch (Exception e) {
				LOG.error("Error: ",e);
			}
		}
		
	}

}
