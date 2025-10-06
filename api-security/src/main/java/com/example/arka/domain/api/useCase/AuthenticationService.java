package com.example.arka.domain.api.useCase;

import com.example.arka.domain.api.IAuthenticationServicePort;
import com.example.arka.domain.spi.IAuthenticationPort;

public class AuthenticationService implements IAuthenticationServicePort {

    private final IAuthenticationPort authenticationPort;

    public AuthenticationService (IAuthenticationPort authenticationPort) {
        this.authenticationPort = authenticationPort;
    }

    public boolean authenticate(String gmail, String password) {
        try {
            authenticationPort.authentication(gmail,password);
            return true;
        } catch (Error e) {
            throw new Error("Invalid credentials");
        }
    }
}
