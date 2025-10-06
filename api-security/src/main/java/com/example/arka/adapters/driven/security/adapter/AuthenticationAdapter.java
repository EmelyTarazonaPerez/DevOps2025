package com.example.arka.adapters.driven.security.adapter;

import com.example.arka.domain.spi.IAuthenticationPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAdapter implements IAuthenticationPort {

    private final AuthenticationManager authenticationManager;

    public AuthenticationAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public boolean authentication(String gmail, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(gmail, password);
        try {
            authenticationManager.authenticate(authentication);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
