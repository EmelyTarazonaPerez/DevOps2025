package com.example.arka.adapters.driving.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String emailAddress;
    private String password;
    private String role;
}
