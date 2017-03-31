package io.pivotal.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.dom.Greeting;

@RestController
public class GreetingServiceController {
	private Greeting greeting;
	
	public GreetingServiceController(Greeting greeting) {
		super();
		this.greeting = greeting;
	}

	@GetMapping("/greeting")
	public Greeting greeting() {
	    return greeting;
	}
}
