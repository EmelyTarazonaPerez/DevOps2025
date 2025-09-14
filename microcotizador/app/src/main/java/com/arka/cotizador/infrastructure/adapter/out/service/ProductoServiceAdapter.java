package com.arka.cotizador.infrastructure.adapter.out.service;

import com.arka.cotizador.domain.model.Product;
import com.arka.cotizador.domain.port.out.ProductoServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceAdapter implements ProductoServicePort {

    private final RestTemplate restTemplate;
    @Value("${microservices.cotizador.url}")
    private final String arkaBaseUrl;


    public ProductoServiceAdapter(RestTemplate restTemplate, String arkaBaseUrl) {
        this.restTemplate = restTemplate;
        this.arkaBaseUrl = arkaBaseUrl;
    }

    @Override
    public List<Product> obtenerProductosPorIds(List<Long> productoIds) {
        return productoIds.stream()
                .map(id -> Optional.ofNullable(
                        restTemplate.getForObject(arkaBaseUrl + "/products/{id}", Product.class, id)))
                .flatMap(Optional::stream) // solo pasa los que no son null
                .collect(Collectors.toList());

    }

    @Override
    public boolean verificarDisponibilidad(Integer productoId, Integer cantidad) {
        Product product = restTemplate.getForObject(arkaBaseUrl + "/products/{id}", Product.class, productoId);
        return product != null && product.getStock() != null && product.getStock() >= cantidad;    }


}
