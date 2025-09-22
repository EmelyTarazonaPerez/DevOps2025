package com.example.demo.service.bff;

import com.example.demo.dto.web.WebProductCommentsDto;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.CommentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WebBffService {

    private final ProductService productService;
    private final CommentService commentService;

    public WebBffService(
                        ProductService productService,
                        CommentService commentService) {

        this.productService = productService;
        this.commentService = commentService;
    }

    /**
     * Obtiene datos para el dashboard web con analíticas y paginación
     */
    public Mono<List<com.example.demo.dto.ProductDto>> searchProducts(String query, Integer page, Integer size) {
        return productService.searchProducts(query, page, size)
                .collectList();
    }

    /**
     * Obtiene todos los comentarios de un producto
     * Específico para cliente web con paginación y analíticas detalladas
     */
    public Mono<WebProductCommentsDto> getProductComments(String productId, Integer page, Integer size, String sortBy) {
        Mono<com.example.demo.dto.ProductDto> productMono = productService.getProductById(productId);
        Mono<List<CommentDto>> commentsMono = commentService
                .getAllComments(productId, page, size, sortBy)
                .collectList();

        return Mono.zip(productMono, commentsMono)
                .map(tuple -> {
                    com.example.demo.dto.ProductDto product = tuple.getT1();
                    List<CommentDto> comments = tuple.getT2();

                    return new WebProductCommentsDto(
                            product.getId(),
                            product.getName(),
                            comments
                    );
                });
    }


}
