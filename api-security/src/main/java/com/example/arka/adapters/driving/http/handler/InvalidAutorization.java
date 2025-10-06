package com.example.arka.adapters.driving.http.handler;

public class InvalidAutorization extends RuntimeException{
    public InvalidAutorization(String message) {
        super(message);
    }
}
