package com.example.arka.adapters.driven.security.adapter;

import com.example.arka.domain.spi.IPasswordEncodePort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncodeAdapter implements IPasswordEncodePort {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncodeAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodeToPasswordEncoder (String password) {
        return passwordEncoder.encode(password);
    }
}
