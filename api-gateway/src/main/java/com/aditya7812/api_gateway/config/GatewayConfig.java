package com.aditya7812.api_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aditya7812.api_gateway.security.AuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))

                .route("product-service", r -> r.path("/api/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://product-service"))

                .route("cart-service", r -> r.path("/api/cart/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://cart-service"))

                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-service"))
                        
                .build();
    }

}