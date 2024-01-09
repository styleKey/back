package com.thekey.stylekeyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StyleKeyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StyleKeyServerApplication.class, args);
	}

}
