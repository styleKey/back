package com.thekey.stylekeyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.thekey.stylekeyserver.config.properties.AppProperties;
import com.thekey.stylekeyserver.config.properties.CorsProperties;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = "com.thekey.stylekeyserver")
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class StyleKeyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StyleKeyServerApplication.class, args);
	}

}
