package com.example.demo.service.bff;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.CommentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MobileBffService {

    private final ProductService productService;
    private final CommentService commentService;

    public MobileBffService(ProductService productService,
                           CommentService commentService) {

        this.productService = productService;
        this.commentService = commentService;
    }

    /**
     * Obtiene el comentario más destacado de un producto específico
     */
    public Mono<CommentDto> getHighlightedComment(int productId) {
        Mono<CommentDto> commentMono = commentService.getHighlightedCommentByProductId(productId);
        return commentMono;
    }

    /**
     * Busca productos con paginación
     */
    public Mono<List<ProductDto>> searchProducts(String query, Integer page, Integer size) {
        return productService.searchProducts(query, page, size)
                .collectList();
    }
}
