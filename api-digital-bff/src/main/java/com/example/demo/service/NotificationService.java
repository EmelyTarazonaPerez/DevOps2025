package com.example.demo.service;

import com.example.demo.dto.mobile.MobileDashboardDto.NotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class NotificationService {

    private final WebClient webClient;
    private final String notificationServiceUrl;

    public NotificationService(WebClient.Builder webClientBuilder,
                              @Value("${microservices.notification-service.url:http://localhost:8084}") String notificationServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(notificationServiceUrl).build();
        this.notificationServiceUrl = notificationServiceUrl;
    }

    public Flux<NotificationDto> getNotificationsByUserId(String userId) {
        return webClient.get()
                .uri("/api/notifications/user/{userId}", userId)
                .retrieve()
                .bodyToFlux(NotificationDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

    public Flux<NotificationDto> getUnreadNotificationsByUserId(String userId) {
        return webClient.get()
                .uri("/api/notifications/user/{userId}/unread", userId)
                .retrieve()
                .bodyToFlux(NotificationDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

    public Mono<NotificationDto> createNotification(NotificationDto notificationDto) {
        return webClient.post()
                .uri("/api/notifications")
                .bodyValue(notificationDto)
                .retrieve()
                .bodyToMono(NotificationDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    public Mono<Void> markAsRead(String notificationId) {
        return webClient.patch()
                .uri("/api/notifications/{id}/read", notificationId)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    public Mono<Void> deleteNotification(String notificationId) {
        return webClient.delete()
                .uri("/api/notifications/{id}", notificationId)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }
}
