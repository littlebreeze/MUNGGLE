package com.munggle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
public class MunggleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MunggleApplication.class, args);
	}
}
