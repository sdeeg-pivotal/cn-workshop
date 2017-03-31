package io.pivotal.api;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.dom.Greeting;

@RestController
public class GreetingServiceController {
	private Greeting greeting;
	private final CounterService counterService;
	
	public GreetingServiceController(Greeting greeting, CounterService counterService) {
		super();
		this.greeting = greeting;
		this.counterService = counterService;
	}

	@GetMapping("/greeting")
	public Greeting greeting() {
		counterService.increment("greeting.service.invoked");
	    return greeting;
	}
}
