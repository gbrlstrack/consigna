package com.consigna.consigna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ConsignaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsignaApplication.class, args);
	}

}
