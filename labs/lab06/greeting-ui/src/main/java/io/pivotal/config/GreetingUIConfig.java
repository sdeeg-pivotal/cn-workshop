package io.pivotal.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableZuulProxy
public class GreetingUIConfig {

}
