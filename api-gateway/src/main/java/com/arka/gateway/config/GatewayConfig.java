package com.arka.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.arka.gateway.utils.AuthenticationFilter;
import static org.springframework.cloud.gateway.route.builder.RouteDslKt.filters;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Hello World Service routes
            .route("hello-world", r -> r
                .path("/api/hello/**")
                .filters(f -> f.stripPrefix(2).filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                .uri("lb://hello-world-service")
            )
            // Cotizador Service routes
            .route("cotizador", r -> r
                .path("/api/cotizador/**")
                .filters(f -> f.stripPrefix(2).filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                .uri("lb://arca-cotizador")
            )
            // Gestor Solicitudes Service routes
            .route("gestor-solicitudes", r -> r
                .path("/api/gestor/**")
                .filters(f -> f.stripPrefix(2).filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                .uri("lb://arca-gestor-solicitudes")
            )
            .route("arka", r -> r
                .path("/api/v1/arka/**")
                .filters(f -> f.stripPrefix(3))
                .uri("lb://arka")
            )
            .route("api-security", r -> r
                .path("api/v1/auth/**")
                .filters(f -> f.stripPrefix(2))
                .uri("lb://api-security")
                )
            // Eureka Dashboard route
            .route("eureka", r -> r
                .path("/eureka/**")
                .uri("http://localhost:8761")
            )
            .build();
    }
}
