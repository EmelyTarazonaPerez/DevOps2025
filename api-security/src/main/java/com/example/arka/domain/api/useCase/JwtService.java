package com.example.arka.domain.api.useCase;

import com.example.arka.domain.api.IJwtServicePort;
import com.example.arka.domain.model.Usuario;
import com.example.arka.domain.spi.IJwtPort;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public class JwtService implements IJwtServicePort {
    private final IJwtPort jwtPort;
    public JwtService(IJwtPort jwtPort) {
        this.jwtPort = jwtPort;
    }

    @Override
    public String getToken(Usuario user) {
        return jwtPort.getToken(user);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtPort.getUsernameFromToken(token);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return jwtPort.isTokenValid(token, userDetails);
    }

    @Override
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        return jwtPort.getClaim(token, claimsResolver);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtPort.validateToken(token);
    }

    @Override
    public String extractRole(String token) {
        return jwtPort.extractRole(token);
    }

    @Override
    public int getUserIdFromToken(String token) {
        return 0;
    }
}
