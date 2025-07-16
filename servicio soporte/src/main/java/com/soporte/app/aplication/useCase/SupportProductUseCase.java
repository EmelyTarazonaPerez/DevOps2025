package com.soporte.app.aplication.useCase;

import com.soporte.app.aplication.out.SupportProductPort;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.domain.servicio.ServiceSupportProduct;
import com.soporte.app.infrastructure.controller.dto.ResponseSupportProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupportProductUseCase {

    private final SupportProductPort clientRepositoryPort;
    private final ServiceSupportProduct serviceSupportProduct;

    public List<ResponseSupportProduct> getAllClients() {
        List<SupportProduct>  clients = clientRepositoryPort.findAllProduct();
        if (clients == null || clients.isEmpty()) {
            throw new RuntimeException("No clients found");
        }
        return clients.stream()
                .map(client -> {
                    return new ResponseSupportProduct(
                            client.getId(),
                            client.getName(),
                            client.getCode(),
                            client.getQuantity(),
                            client.getUnitPrice(),
                            client.getSupplierId()
                    );
                })
                .collect(Collectors.toList());
    }

    public ResponseSupportProduct getClientById(Integer id) {
        SupportProduct client = clientRepositoryPort.findClientById(id);
        if (client == null) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        return serviceSupportProduct.getResponseSupportProduct(client);
    }

    public ResponseSupportProduct addProduct(SupportProduct supportProduct) {
        if (supportProduct == null) {
            throw new IllegalArgumentException("Client details cannot be null");
        }
        SupportProduct result = clientRepositoryPort.saveClient(supportProduct);
        return serviceSupportProduct.getResponseSupportProduct(result);
    }

    public ResponseSupportProduct updateProduct(Integer id, SupportProduct supportProduct) {
        SupportProduct clientUpdate = clientRepositoryPort.updateClient(supportProduct, id);
        if (clientUpdate == null) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        return serviceSupportProduct.getResponseSupportProduct(clientUpdate);
    }

    public String deleteClient(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        return clientRepositoryPort.deleteClient(id);
    }

    public List<ResponseSupportProduct> filterClientByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }
        List<SupportProduct> supportProduct = clientRepositoryPort.findClientByName(name);
        return supportProduct.stream()
                .map(product -> new ResponseSupportProduct(
                        product.getId(),
                        product.getName(),
                        product.getCode(),
                        product.getQuantity(),
                        product.getUnitPrice(),
                        product.getSupplierId()

                ))
                .collect(Collectors.toList());
    }

    public List<ResponseSupportProduct> getProductByOrderAsc(boolean orderAsc) {
    List<SupportProduct> clients = clientRepositoryPort.findAllProduct();
        if (clients == null || clients.isEmpty()) {
            throw new RuntimeException("No clients found");
        }
        if (orderAsc) {
            clients.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        } else {
            clients.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
        }
        return clients.stream()
                .map(client -> {
                    return new ResponseSupportProduct(
                            client.getId(),
                            client.getName(),
                            client.getCode(),
                            client.getQuantity(),
                            client.getUnitPrice(),
                            client.getSupplierId()

                    );
                })
                .collect(Collectors.toList());
    }

    public List<ResponseSupportProduct> getProductByPrice(Float startRange, Float endRange) {
        if (startRange == null || endRange == null || startRange < 0 || endRange < 0) {
            throw new IllegalArgumentException("Price cannot be null or empty");
        }
        List<SupportProduct> products = clientRepositoryPort.findProductByPrice(startRange, endRange);
        return products.stream()
                .map(product -> new ResponseSupportProduct(
                        product.getId(),
                        product.getName(),
                        product.getCode(),
                        product.getQuantity(),
                        product.getUnitPrice(),
                        product.getSupplierId()

                ))
                .collect(Collectors.toList());
    }
}
