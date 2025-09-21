package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class ProductService {

    private final WebClient webClient;
    private final String productServiceUrl;

    public ProductService(WebClient.Builder webClientBuilder,
                         @Value("${microservices.product-service.url:http://localhost:8083}") String productServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(productServiceUrl).build();
        this.productServiceUrl = productServiceUrl;
    }

    public Mono<ProductDto> getProductById(String productId) {
        return webClient.get()
                .uri("/api/support/products/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> {
                    // Fallback response
                    return Mono.just(new ProductDto(productId, "Unknown Product", "No description available", null, "Unknown", "/default-product.png", 0));
                });
    }


    public Flux<ProductDto> searchProducts(String query, Integer page, Integer size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/support/products")
                        .queryParam("q", query)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

}
