package io.pivotal.api;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.pivotal.dom.Greeting;

@RestController
public class GreetingUiApi {
	private final RestTemplate restTemplate;
	private final Greeting fallbackGreeting;
	private final Tracer tracer;

	public GreetingUiApi(RestTemplate restTemplate, Greeting fallbackGreeting, Tracer tracer) {
		super();
		this.restTemplate = restTemplate;
		this.fallbackGreeting = fallbackGreeting;
		this.tracer = tracer;
	}
	
	public Greeting getGreetingFallback() {
		return fallbackGreeting;
	}

	@GetMapping("/greeting")
	@HystrixCommand(fallbackMethod = "getGreetingFallback")
	public Greeting getGreeting() {
	    Greeting greeting = null;
		Span span = null;
		try {
			span = tracer.createSpan("callingBackOfficeGreetingService_span");
			span.logEvent("call_backOfficeGreetingService");
			greeting = restTemplate.getForObject("http://greeting-service/greeting", Greeting.class);
			span.logEvent("response_received_backOfficeGreetingService");
			tracer.addTag("ui","success");
		}finally {
			tracer.close(span);
		}
		greeting.greeting += "-transformed";
		return greeting;
	}
}
