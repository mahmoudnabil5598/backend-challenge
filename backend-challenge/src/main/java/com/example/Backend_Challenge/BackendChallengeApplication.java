package com.example.backend_challenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class BackendChallengeApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackendChallengeApplication.class, args);
		log.info("Server is running");
	}

}