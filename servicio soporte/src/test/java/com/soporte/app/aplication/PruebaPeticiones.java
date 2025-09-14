package com.soporte.app.aplication;

import com.soporte.app.aplication.useCase.SupportProductUseCase;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.domain.port.out.SupportProductPort;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import com.soporte.app.utils.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PruebaPeticiones {

    @Mock
    private SupportProductPort productRepositoryPort;

    @Mock
    private Function serviceSupportProduct;

    @InjectMocks
    private SupportProductUseCase productUseCase;

    // getAllProducts()
    @Test
    void getAllProducts_ShouldReturnMappedList_WhenProductsExist() {
        // Arrange
        SupportProduct product = new SupportProduct(1, "Producto", "001", 10, 100.0f, "1", "Electrónica");
        List<SupportProduct> productList = List.of(product);
        ResponseSupportProduct response = new ResponseSupportProduct(1, "Producto", "001", 10, 100.0f, "1", "Electrónica");

        Mockito.when(productRepositoryPort.findAllProduct()).thenReturn(productList);

        // Act
        List<ResponseSupportProduct> result = productUseCase.getAllProducts();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Producto", result.get(0).getName());
    }

    @Test
    void getAllProducts_ShouldThrowException_WhenListIsEmpty() {
        Mockito.when(productRepositoryPort.findAllProduct()).thenReturn(List.of());

        assertThrows(RuntimeException.class, () -> productUseCase.getAllProducts());
    }

    // getProductById()
    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        SupportProduct product = new SupportProduct(1, "Prod", "002", 5, 50.0f, "2", "Categoria");
        ResponseSupportProduct response = new ResponseSupportProduct(1, "Prod", "002", 5, 50.0f, "2", "Categoria");

        Mockito.when(productRepositoryPort.findProductById(1)).thenReturn(product);
        Mockito.when(serviceSupportProduct.getResponseSupportProduct(product)).thenReturn(response);

        ResponseSupportProduct result = productUseCase.getProductById(1);

        assertEquals("Prod", result.getName());
    }

    @Test
    void getProductById_ShouldThrowException_WhenNotFound() {
        Mockito.when(productRepositoryPort.findProductById(99)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> productUseCase.getProductById(99));
    }

    // addProduct()
    @Test
    void addProduct_ShouldSaveAndReturnProduct() {
        SupportProduct input = new SupportProduct(1, "Nuevo", "003", 20, 200.0f, "3", "Categoria");
        ResponseSupportProduct response = new ResponseSupportProduct(1, "Nuevo", "003", 20, 200.0f, "3", "Categoria");

        Mockito.when(productRepositoryPort.saveProduct(input)).thenReturn(input);
        Mockito.when(serviceSupportProduct.getResponseSupportProduct(input)).thenReturn(response);

        ResponseSupportProduct result = productUseCase.addProduct(input);

        assertEquals("Nuevo", result.getName());
    }

    @Test
    void addProduct_ShouldThrowException_WhenNullInput() {
        assertThrows(IllegalArgumentException.class, () -> productUseCase.addProduct(null));
    }

    // updateProduct()
    @Test
    void updateProduct_ShouldReturnUpdatedProduct() {
        SupportProduct updated = new SupportProduct(1, "Editado", "004", 15, 150.0f, "4", "Categoria");
        ResponseSupportProduct response = new ResponseSupportProduct(1, "Editado", "004", 15, 150.0f, "4", "Categoria");

        Mockito.when(productRepositoryPort.updateProduct(updated, 1)).thenReturn(updated);
        Mockito.when(serviceSupportProduct.getResponseSupportProduct(updated)).thenReturn(response);

        ResponseSupportProduct result = productUseCase.updateProduct(1, updated);

        assertEquals("Editado", result.getName());
    }

    @Test
    void updateProduct_ShouldThrowException_WhenUpdateFails() {
        Mockito.when(productRepositoryPort.updateProduct(Mockito.any(), Mockito.eq(1))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> productUseCase.updateProduct(1, new SupportProduct()));
    }

    // deleteProduct()
    @Test
    void deleteProduct_ShouldReturnMessage() {
        Mockito.when(productRepositoryPort.deleteProduct(1)).thenReturn("Deleted");

        String result = productUseCase.deleteProduct(1);

        assertEquals("Deleted", result);
    }

    @Test
    void deleteProduct_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productUseCase.deleteProduct(null));
    }
}
