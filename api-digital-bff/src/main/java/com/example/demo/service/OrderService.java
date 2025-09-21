package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final WebClient webClient;
    private final String orderServiceUrl;

    public OrderService(WebClient.Builder webClientBuilder,
                       @Value("${microservices.order-service.url:http://localhost:8083}") String orderServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(orderServiceUrl).build();
        this.orderServiceUrl = orderServiceUrl;
    }

    public Mono<OrderDto> getOrderById(String orderId) {
        return webClient.get()
                .uri("/api/orders/{id}", orderId)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> {
                    // Fallback response
                    return Mono.just(new OrderDto(orderId, "unknown", "unknown", 0, 0.0, 
                                                "UNKNOWN", LocalDateTime.now(), "Unknown Address"));
                });
    }

    public Flux<OrderDto> getOrdersByUserId(String userId) {
        return webClient.get()
                .uri("/api/orders/user/{userId}", userId)
                .retrieve()
                .bodyToFlux(OrderDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

    public Flux<OrderDto> getRecentOrdersByUserId(String userId, Integer limit) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/orders/user/{userId}/recent")
                        .queryParam("limit", limit)
                        .build(userId))
                .retrieve()
                .bodyToFlux(OrderDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

    public Mono<OrderDto> createOrder(OrderDto orderDto) {
        return webClient.post()
                .uri("/api/orders")
                .bodyValue(orderDto)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    public Mono<OrderDto> updateOrderStatus(String orderId, String status) {
        return webClient.patch()
                .uri("/api/orders/{id}/status", orderId)
                .bodyValue(status)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    public Mono<Long> countOrdersByUserId(String userId) {
        return webClient.get()
                .uri("/api/orders/user/{userId}/count", userId)
                .retrieve()
                .bodyToMono(Long.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorReturn(0L);
    }

    public Mono<Double> getTotalSpentByUserId(String userId) {
        return webClient.get()
                .uri("/api/orders/user/{userId}/total-spent", userId)
                .retrieve()
                .bodyToMono(Double.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorReturn(0.0);
    }
}
