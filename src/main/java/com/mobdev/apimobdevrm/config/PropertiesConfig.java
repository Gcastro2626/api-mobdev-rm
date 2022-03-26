package com.mobdev.apimobdevrm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mobdev.apimobdevrm.model.PropertiesApiRm;

@Configuration
public class PropertiesConfig {
	@Bean
    @ConfigurationProperties(prefix = "api-rm")
    public PropertiesApiRm propertiesApiRm() {
        return new PropertiesApiRm();
    }
}
