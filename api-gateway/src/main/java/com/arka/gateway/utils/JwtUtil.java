package com.arka.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924"; // ponla en application.yml mejor

    // Extraer claims del token
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Validar si el token es válido
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (SignatureException e) {
            System.out.println("Firma inválida");
        } catch (Exception e) {
            System.out.println("Token inválido");
        }
        return false;
    }

    // Obtener el usuario del token
    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }
}
