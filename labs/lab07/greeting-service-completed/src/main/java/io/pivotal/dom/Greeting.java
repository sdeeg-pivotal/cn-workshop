package io.pivotal.dom;

import org.springframework.beans.factory.annotation.Value;

public class Greeting {
	@Value("${app.greeting:Doh!}")
	public String greeting = "hello, world";
}
