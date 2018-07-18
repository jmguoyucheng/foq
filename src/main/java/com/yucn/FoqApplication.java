package com.yucn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoqApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoqApplication.class, args);
	}
}
