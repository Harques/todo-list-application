package com.microservices.api.config;

import com.microservices.api.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("user-service", r -> r.path("/user/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("to-do-service", r -> r.path("/todo/**").filters(f -> f.filter(filter)).uri("lb://hello")).build();
    }

}