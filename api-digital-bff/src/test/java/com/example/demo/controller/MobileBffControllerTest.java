package com.example.demo.controller;

import com.example.demo.service.bff.MobileBffService;
import com.example.demo.dto.mobile.MobileDashboardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

@WebFluxTest(MobileBffController.class)
public class MobileBffControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MobileBffService mobileBffService;

    @Test
    public void testGetMobileDashboard() {
        // Given
        String userId = "user123";
        MobileDashboardDto mockDashboard = new MobileDashboardDto();
        
        when(mobileBffService.getMobileDashboard(anyString()))
                .thenReturn(Mono.just(mockDashboard));

        // When & Then
        webTestClient.get()
                .uri("/api/mobile/v1/dashboard/{userId}", userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MobileDashboardDto.class);
    }

    @Test
    public void testGetSimpleMobileDashboard() {
        // Given
        String userId = "user123";
        MobileDashboardDto mockDashboard = new MobileDashboardDto();
        
        when(mobileBffService.getSimpleMobileDashboard(anyString()))
                .thenReturn(Mono.just(mockDashboard));

        // When & Then
        webTestClient.get()
                .uri("/api/mobile/v1/dashboard/{userId}/simple", userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MobileDashboardDto.class);
    }

    @Test
    public void testHealthEndpoint() {
        // When & Then
        webTestClient.get()
                .uri("/api/mobile/v1/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> response.contains("mobile-bff"));
    }
}
