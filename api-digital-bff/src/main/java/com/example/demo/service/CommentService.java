package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CommentService {

    private final WebClient webClient;
    private final String commentServiceUrl;

    public CommentService(WebClient.Builder webClientBuilder,
                         @Value("${microservices.comment-service.url:http://localhost:8084}") String commentServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(commentServiceUrl).build();
        this.commentServiceUrl = commentServiceUrl;
    }

    /**
     * Obtiene el comentario más destacado de un producto específico
     * Endpoint usado por el BFF móvil
     */
    public Mono<CommentDto> getHighlightedCommentByProductId(int productId) {
        return webClient.get()
                .uri("/comentarios/{productoId}/destacado", productId)
                .retrieve()
                .bodyToMono(CommentDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> {
                    // Fallback response para comentario destacado
                    return Mono.just(createFallbackHighlightedComment(productId));
                });
    }

    /**
     * Obtiene todos los comentarios de un producto con paginación
     * Endpoint usado por el BFF web
     */
    public Flux<CommentDto> getAllComments(String productId, Integer page, Integer size, String sortBy) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/comentarios")
                        .queryParam("page", page != null ? page : 0)
                        .queryParam("size", size != null ? size : 20)
                        .build(productId))
                .retrieve()
                .bodyToFlux(CommentDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

    /**
     * Obtiene comentarios por usuario
     */
    public Flux<CommentDto> getCommentsByUserId(String userId) {
        return webClient.get()
                .uri("/api/comments/user/{userId}", userId)
                .retrieve()
                .bodyToFlux(CommentDto.class)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(throwable -> Flux.empty());
    }

    /**
     * Obtiene la cantidad total de comentarios de un producto
     */
    public Mono<Long> getCommentCountByProductId(String productId) {
        return webClient.get()
                .uri("/api/comments/product/{productId}/count", productId)
                .retrieve()
                .bodyToMono(Long.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorReturn(0L);
    }

    /**
     * Obtiene el promedio de calificaciones de un producto
     */
    public Mono<Double> getAverageRatingByProductId(String productId) {
        return webClient.get()
                .uri("/api/comments/product/{productId}/average-rating", productId)
                .retrieve()
                .bodyToMono(Double.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorReturn(0.0);
    }

    /**
     * Obtiene la distribución de calificaciones de un producto
     */
    public Mono<RatingDistribution> getRatingDistributionByProductId(String productId) {
        return webClient.get()
                .uri("/api/comments/product/{productId}/rating-distribution", productId)
                .retrieve()
                .bodyToMono(RatingDistribution.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorReturn(new RatingDistribution(0, 0, 0, 0, 0));
    }

    /**
     * Crea un nuevo comentario
     */
    public Mono<CommentDto> createComment(CommentDto commentDto) {
        return webClient.post()
                .uri("/api/comments")
                .bodyValue(commentDto)
                .retrieve()
                .bodyToMono(CommentDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    /**
     * Actualiza un comentario existente
     */
    public Mono<CommentDto> updateComment(String commentId, CommentDto commentDto) {
        return webClient.put()
                .uri("/api/comments/{id}", commentId)
                .bodyValue(commentDto)
                .retrieve()
                .bodyToMono(CommentDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    /**
     * Da like a un comentario
     */
    public Mono<CommentDto> likeComment(String commentId) {
        return webClient.patch()
                .uri("/api/comments/{id}/like", commentId)
                .retrieve()
                .bodyToMono(CommentDto.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    /**
     * Elimina un comentario
     */
    public Mono<Void> deleteComment(String commentId) {
        return webClient.delete()
                .uri("/api/comments/{id}", commentId)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)));
    }

    /**
     * Método helper para crear comentario fallback cuando falla el servicio
     */
    private CommentDto createFallbackHighlightedComment(int productId) {
        return new CommentDto("0", "Sistema", productId,
                "No hay comentarios destacados disponibles en este momento.",
                0, LocalDateTime.now()
        );
    }

    /**
     * Clase auxiliar para distribución de calificaciones
     */
    public static class RatingDistribution {
        private Integer fiveStars;
        private Integer fourStars;
        private Integer threeStars;
        private Integer twoStars;
        private Integer oneStar;

        public RatingDistribution() {}

        public RatingDistribution(Integer fiveStars, Integer fourStars, Integer threeStars, 
                                Integer twoStars, Integer oneStar) {
            this.fiveStars = fiveStars;
            this.fourStars = fourStars;
            this.threeStars = threeStars;
            this.twoStars = twoStars;
            this.oneStar = oneStar;
        }

        // Getters and Setters
        public Integer getFiveStars() { return fiveStars; }
        public void setFiveStars(Integer fiveStars) { this.fiveStars = fiveStars; }
        
        public Integer getFourStars() { return fourStars; }
        public void setFourStars(Integer fourStars) { this.fourStars = fourStars; }
        
        public Integer getThreeStars() { return threeStars; }
        public void setThreeStars(Integer threeStars) { this.threeStars = threeStars; }
        
        public Integer getTwoStars() { return twoStars; }
        public void setTwoStars(Integer twoStars) { this.twoStars = twoStars; }
        
        public Integer getOneStar() { return oneStar; }
        public void setOneStar(Integer oneStar) { this.oneStar = oneStar; }
    }
}
