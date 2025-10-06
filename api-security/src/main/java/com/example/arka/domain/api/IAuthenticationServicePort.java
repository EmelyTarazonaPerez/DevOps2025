package com.example.arka.domain.api;

public interface IAuthenticationServicePort {
    boolean authenticate(String gmail, String password);
}
