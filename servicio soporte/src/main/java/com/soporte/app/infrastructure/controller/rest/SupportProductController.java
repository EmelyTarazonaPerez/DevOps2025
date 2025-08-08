package com.soporte.app.infrastructure.controller.rest;

import com.soporte.app.aplication.useCase.SupportProductUseCase;
import com.soporte.app.infrastructure.controller.dto.request.RequestSupportProduct;
import com.soporte.app.infrastructure.controller.dto.response.ResponseSupportProduct;

import com.soporte.app.infrastructure.controller.mapping.IMapperProduct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/support/products")
public class SupportProductController {
    private final SupportProductUseCase serviceSupportProduct;
    private final IMapperProduct mapperSupportProduct;

    @GetMapping
    public ResponseEntity<List<ResponseSupportProduct>> getAll() {
        List<ResponseSupportProduct> result = serviceSupportProduct.getAllProducts();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>( result, HttpStatus.OK); // HttpStatus 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
            try {
                ResponseSupportProduct result = serviceSupportProduct.getProductById(id);
                return ResponseEntity.ok(result); // 200 OK
            } catch (RuntimeException e) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "result con ID " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404 con mensaje
            }
    }

    @PostMapping
    public ResponseEntity<ResponseSupportProduct> newProduct(@RequestBody RequestSupportProduct product) {
        ResponseSupportProduct Update = serviceSupportProduct.addProduct(mapperSupportProduct.requestToModel(product));
        if (Update == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        return new ResponseEntity<>(Update, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSupportProduct> updateProduct(@PathVariable Integer id, @RequestBody RequestSupportProduct movie) {
        try {
            if (id == null || movie == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseSupportProduct updated = serviceSupportProduct.updateProduct(id, mapperSupportProduct.requestToModel(movie));
            return ResponseEntity.ok(updated); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(serviceSupportProduct.deleteProduct(id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND); // 404 Not Found;
        }
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<List<ResponseSupportProduct>> getClientByProduct(@PathVariable String name) {
        try {
            return ResponseEntity.ok(serviceSupportProduct.filterByName(name));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order")
    public ResponseEntity<List<ResponseSupportProduct>> getProductByOrderAsc(@RequestParam(defaultValue = "true") boolean orderAsc) {
        try {
            List<ResponseSupportProduct> clients = serviceSupportProduct.getProductByOrderAsc(orderAsc);
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(clients, HttpStatus.OK); // HttpStatus 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND); // 404 Not Found;
        }
    }

    @GetMapping("/filter/price/{startRange}/and/{endRange}")
    public ResponseEntity<List<ResponseSupportProduct>> getProductByPrice(@PathVariable("startRange") Float startRange,
                                                                          @PathVariable("endRange") Float endRange) {
        try {
            List<ResponseSupportProduct> products = serviceSupportProduct.getProductByPrice(startRange, endRange);
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(products, HttpStatus.OK); // HttpStatus 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND); // 404 Not Found;
        }
    }



}
