package com.example.demo.controller;

import com.example.demo.dto.mobile.MobileDashboardDto;
import com.example.demo.dto.mobile.MobileDashboardDto.NotificationDto;
import com.example.demo.dto.mobile.MobileHighlightedCommentDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.service.bff.MobileBffService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/v1")
@CrossOrigin(origins = "*")
public class MobileBffController {

    private final MobileBffService mobileBffService;

    public MobileBffController(MobileBffService mobileBffService) {
        this.mobileBffService = mobileBffService;
    }

    @GetMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<com.example.demo.dto.ProductDto>> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return mobileBffService.searchProducts(query, page, size);
    }

    /**
     * Obtiene el comentario más destacado de un producto específico
     * Optimizado para cliente móvil
     */
    @GetMapping(value = "/products/{productId}/highlighted-comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CommentDto> getHighlightedComment(@PathVariable int productId) {
        return mobileBffService.getHighlightedComment(productId);
    }


    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> health() {
        return Mono.just("{\"status\":\"UP\",\"service\":\"mobile-bff\"}");
    }
}
