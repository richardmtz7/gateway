package com.gateway.lite.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	@Bean
	public RouteLocator  customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
	            .route("ms-business", r -> r.path("/api/business/**")
	                .uri("http://localhost:8081"))
	            .route("ms-auth", r -> r.path("/api/auth/**")
	                .uri("http://localhost:8082"))
	            .build();
	}
}
