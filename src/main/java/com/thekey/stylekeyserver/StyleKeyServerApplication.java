package com.thekey.stylekeyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = "com.thekey.stylekeyserver")
public class StyleKeyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StyleKeyServerApplication.class, args);
	}

}
