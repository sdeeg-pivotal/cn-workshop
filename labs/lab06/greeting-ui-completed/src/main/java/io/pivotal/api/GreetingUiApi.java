package io.pivotal.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.pivotal.dom.Greeting;

@RestController
public class GreetingUiApi {
	private RestTemplate restTemplate;
	private Greeting fallbackGreeting;

	public GreetingUiApi(RestTemplate restTemplate, Greeting fallbackGreeting) {
		super();
		this.restTemplate = restTemplate;
		this.fallbackGreeting = fallbackGreeting;
	}
	
	public Greeting getGreetingFallback() {
		return fallbackGreeting;
	}

	@GetMapping("/greeting")
	@HystrixCommand(fallbackMethod = "getGreetingFallback")
	public Greeting getGreeting() {
		Greeting greeting = restTemplate.getForObject("http://greeting-service/greeting", Greeting.class);
		greeting.greeting += "-transformed";
		return greeting;
	}

}
