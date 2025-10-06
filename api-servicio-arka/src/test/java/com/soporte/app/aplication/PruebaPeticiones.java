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

import java.math.BigDecimal;
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
        SupportProduct product = new SupportProduct(1L, "Producto", "001", 10, BigDecimal.valueOf(100.0f), "1", "Electrónica", null);
        List<SupportProduct> productList = List.of(product);
        ResponseSupportProduct response = new ResponseSupportProduct(1L, "Producto", "001", 10, BigDecimal.valueOf(100.0f), "1", "Electrónica", null);

        Mockito.when(productRepositoryPort.findAllProduct(0, 25, "DESC")).thenReturn(productList);

        // Act
        List<ResponseSupportProduct> result = productUseCase.getAllProducts(0, 25, "DESC");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Producto", result.get(0).getName());
    }

    @Test
    void getAllProducts_ShouldThrowException_WhenListIsEmpty() {
        Mockito.when(productRepositoryPort.findAllProduct(0, 10,  "DESC")).thenReturn(List.of());

        assertThrows(RuntimeException.class, () -> productUseCase.getAllProducts(0, 10, "DESC"));
    }

    // getProductById()
    @Test
    void getProductById_ShouldReturnProduct_WhenExists() {
        SupportProduct product = new SupportProduct(1L, "Prod", "002", 5, BigDecimal.valueOf(50.0f), "2", "Categoria", null );
        ResponseSupportProduct response = new ResponseSupportProduct(1L, "Prod", "002", 5, BigDecimal.valueOf(50.0f), "2", "Categoria", null);

        Mockito.when(productRepositoryPort.findProductById(1L)).thenReturn(product);
        Mockito.when(serviceSupportProduct.getResponseSupportProduct(product)).thenReturn(response);

        ResponseSupportProduct result = productUseCase.getProductById(1);

        assertEquals("Prod", result.getName());
    }

    @Test
    void getProductById_ShouldThrowException_WhenNotFound() {
        Mockito.when(productRepositoryPort.findProductById(99L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> productUseCase.getProductById(99));
    }

    // addProduct()
    @Test
    void addProduct_ShouldSaveAndReturnProduct() {
        SupportProduct input = new SupportProduct(1L, "Nuevo", "003", 20, BigDecimal.valueOf(200.0f), "3", "Categoria");
        ResponseSupportProduct response = new ResponseSupportProduct(1L, "Nuevo", "003", 20, BigDecimal.valueOf(200.0f), "3", "Categoria");

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
        SupportProduct updated = new SupportProduct(1L, "Editado", "004", 15, BigDecimal.valueOf(150.0), "4", "Categoria");
        ResponseSupportProduct response = new ResponseSupportProduct(1L, "Editado", "004", 15, BigDecimal.valueOf(150.0), "4", "Categoria");

        Mockito.when(productRepositoryPort.updateProduct(updated, 1L)).thenReturn(updated);
        Mockito.when(serviceSupportProduct.getResponseSupportProduct(updated)).thenReturn(response);

        ResponseSupportProduct result = productUseCase.updateProduct(1L, updated);

        assertEquals("Editado", result.getName());
    }

    @Test
    void updateProduct_ShouldThrowException_WhenUpdateFails() {
        Mockito.when(productRepositoryPort.updateProduct(Mockito.any(), Mockito.eq(1L))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> productUseCase.updateProduct(1L, new SupportProduct()));
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
