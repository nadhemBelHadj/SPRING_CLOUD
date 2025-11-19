package com.nadhem.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }


    @Bean
    public RouteLocator MyRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/api/departments/**")
                        .uri("lb://DEPARTMENT"))
                .route(p -> p
                        .path("/api/teachers/**")
                        .filters( f -> f.circuitBreaker(config ->
                                        config.setName("teacherCircuitBreaker")
                                              .setFallbackUri("forward:/contactAdmin") ))
                        .uri("lb://TEACHER"))
                .build();
    }


}
