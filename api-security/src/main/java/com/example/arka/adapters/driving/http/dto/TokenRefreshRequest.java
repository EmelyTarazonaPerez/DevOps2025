package com.example.arka.adapters.driving.http.dto;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String accessToken;
    private String refreshToken;
}
