package com.soporte.app.aplication.useCase;

import com.soporte.app.domain.port.out.SupportProductPort;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.utils.Function;
import com.soporte.app.infrastructure.controller.dto.response.ResponseSupportProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupportProductUseCase {

    private final SupportProductPort productRepositoryPort;
    private final Function serviceSupportProduct;

    public List<ResponseSupportProduct> getAllProducts() {
        List<SupportProduct>  products = productRepositoryPort.findAllProduct();
        if (products == null || products.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        return products.stream()
                .map(product -> {
                    return new ResponseSupportProduct(
                            product.getId(),
                            product.getName(),
                            product.getCode(),
                            product.getQuantity(),
                            product.getUnitPrice(),
                            product.getSupplierId(),
                            product.getCategory()
                    );
                })
                .collect(Collectors.toList());
    }

    public ResponseSupportProduct getProductById(Integer id) {
        SupportProduct product = productRepositoryPort.findProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return serviceSupportProduct.getResponseSupportProduct(product);
    }

    public ResponseSupportProduct addProduct(SupportProduct supportProduct) {
        if (supportProduct == null) {
            throw new IllegalArgumentException("Product details cannot be null");
        }
        SupportProduct result = productRepositoryPort.saveProduct(supportProduct);
        return serviceSupportProduct.getResponseSupportProduct(result);
    }

    public ResponseSupportProduct updateProduct(Integer id, SupportProduct supportProduct) {
        SupportProduct productUpdate = productRepositoryPort.updateProduct(supportProduct, id);
        if (productUpdate == null) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        return serviceSupportProduct.getResponseSupportProduct(productUpdate);
    }

    public String deleteProduct(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        return productRepositoryPort.deleteProduct(id);
    }

    public List<ResponseSupportProduct> filterByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("product name cannot be null or empty");
        }
        List<SupportProduct> supportProduct = productRepositoryPort.findProductByName(name);
        return supportProduct.stream()
                .map(product -> new ResponseSupportProduct(
                        product.getId(),
                        product.getName(),
                        product.getCode(),
                        product.getQuantity(),
                        product.getUnitPrice(),
                        product.getSupplierId(),
                        product.getCategory()
                ))
                .collect(Collectors.toList());
    }

    public List<ResponseSupportProduct> getProductByOrderAsc(boolean orderAsc) {
    List<SupportProduct> product = productRepositoryPort.findAllProduct();
        if (product == null || product.isEmpty()) {
            throw new RuntimeException("No products found");
        }
        if (orderAsc) {
            product.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        } else {
            product.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
        }
        return product.stream()
                .map(client -> {
                    return new ResponseSupportProduct(
                            client.getId(),
                            client.getName(),
                            client.getCode(),
                            client.getQuantity(),
                            client.getUnitPrice(),
                            client.getSupplierId(),
                            client.getCategory()
                    );
                })
                .collect(Collectors.toList());
    }

    public List<ResponseSupportProduct> getProductByPrice(Float startRange, Float endRange) {
        if (startRange == null || endRange == null || startRange < 0 || endRange < 0) {
            throw new IllegalArgumentException("Price cannot be null or empty");
        }
        List<SupportProduct> products = productRepositoryPort.findProductByPrice(startRange, endRange);
        return products.stream()
                .map(product -> new ResponseSupportProduct(
                        product.getId(),
                        product.getName(),
                        product.getCode(),
                        product.getQuantity(),
                        product.getUnitPrice(),
                        product.getSupplierId(),
                        product.getCategory()

                ))
                .collect(Collectors.toList());
    }
}
