package com.thekey.stylekeyserver.common.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class PropertiesConfig {
}
