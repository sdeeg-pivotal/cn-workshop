package io.pivotal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pivotal.dom.Greeting;

@Configuration
public class GreetingServiceConfig {

	@Bean
	public Greeting createGreeting() {
		return new Greeting();
	}
}
