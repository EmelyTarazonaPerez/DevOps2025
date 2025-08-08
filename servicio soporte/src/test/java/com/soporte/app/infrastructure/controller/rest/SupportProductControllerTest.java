package com.soporte.app.infrastructure.controller.rest;

import com.soporte.app.aplication.useCase.SupportProductUseCase;
import com.soporte.app.infrastructure.controller.dto.response.ResponseSupportProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SupportProductControllerTest {
    @Mock
    private SupportProductUseCase serviceSupportProduct;

    @InjectMocks
    private SupportProductController supportProductController;

    @Test
    void getAll_ReturnsListOfProducts_WhenProductsExist() {
        // Arrange
        List<ResponseSupportProduct> mockProducts = List.of(
                new ResponseSupportProduct(),
                new ResponseSupportProduct()
        );
        Mockito.when(serviceSupportProduct.getAllProducts()).thenReturn(mockProducts);

        // Act
        ResponseEntity<List<ResponseSupportProduct>> response = supportProductController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

    }

}
