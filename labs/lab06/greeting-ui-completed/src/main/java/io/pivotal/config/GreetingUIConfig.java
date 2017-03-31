package io.pivotal.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.pivotal.dom.Greeting;

@Configuration
@EnableDiscoveryClient
@EnableZuulProxy
@EnableCircuitBreaker
public class GreetingUIConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
	@Bean
	public Greeting fallbackGreeting() {
		Greeting fallback = new Greeting();
		fallback.greeting = "Don't Panic!";
		return fallback;
	}
}
