package com.dinhnguyendev.microservice.apigateway.configuration;


import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

        @Bean
        public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

            return builder.routes()
                    .route( p-> p.path("/get")
                            .filters(f -> f.addRequestHeader("MyHeader","MyHeaderURL"))
                            .uri("http://localhost:8000/currency-exchange/from/USD/to/VND"))
                    .route(p->p.path("/currency-exchange/**")
                            .uri("lb://currency-exchange")
                    )
                    .route(p->p.path("/currency-exchange-feign/**")
                            .uri("lb://currency-exchange")
                    )
                    .route(p->p.path("/currency-conversion/**")
                            .uri("lb://currency-conversion")
                    )
                    .route(p->p.path("/currency-conversion-feign/**")
                            .uri("lb://currency-conversion")
                    )
                    .build();
    }
}
