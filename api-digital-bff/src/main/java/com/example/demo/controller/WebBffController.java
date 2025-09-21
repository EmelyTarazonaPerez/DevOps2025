package com.example.demo.controller;

import com.example.demo.dto.web.WebDashboardDto;
import com.example.demo.dto.web.WebDashboardDto.AnalyticsDto;
import com.example.demo.dto.web.WebProductCommentsDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.service.bff.WebBffService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/web/v1")
@CrossOrigin(origins = "*")
public class WebBffController {

    private final WebBffService webBffService;

    public WebBffController(WebBffService webBffService) {
        this.webBffService = webBffService;
    }

    @GetMapping(value = "/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<com.example.demo.dto.ProductDto>> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        return webBffService.searchProducts(query, page, size);
    }

    /**
     * Obtiene todos los comentarios de un producto con información completa
     * Específico para cliente web con paginación y analíticas detalladas
     */
    @GetMapping(value = "/products/{productId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WebProductCommentsDto> getProductComments(
            @PathVariable String productId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return webBffService.getProductComments(productId, page, size, sortBy);
    }


    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> health() {
        return Mono.just("{\"status\":\"UP\",\"service\":\"web-bff\"}");
    }
}
