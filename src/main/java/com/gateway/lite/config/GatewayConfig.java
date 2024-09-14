package com.gateway.lite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GatewayConfig implements WebMvcConfigurer {
	
	@Value("${BUSINESS_SERVICE_URI}")
    private String businessServiceUri;

    @Value("${AUTH_SERVICE_URI}")
    private String authServiceUri;
    
    @Value("${url.front}")
	private String urlFront;
    
	@Bean
	public RouteLocator  customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
	            .route("ms-business", r -> r.path("/api/business/**")
	                .uri(businessServiceUri))
	            .route("ms-auth", r -> r.path("/api/auth/**")
	                .uri(authServiceUri))
	            .build();
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(urlFront)
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
