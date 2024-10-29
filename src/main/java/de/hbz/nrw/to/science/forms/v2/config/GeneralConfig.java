package de.hbz.nrw.to.science.forms.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeneralConfig {
	
	@Bean
	WebClient getWebClientBuilder() {
		return WebClient.builder().build();
	}

}
