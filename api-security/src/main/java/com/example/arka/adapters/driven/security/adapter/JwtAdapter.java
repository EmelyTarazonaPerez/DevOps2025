package com.example.arka.adapters.driven.security.adapter;

import com.example.arka.domain.model.Usuario;
import com.example.arka.domain.spi.IJwtPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtAdapter implements IJwtPort {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(Usuario user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, Usuario user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmailAddress())
                .claim("rol", user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getEmailAddress())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // 7 días
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractRole(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return claims.getBody().get("rol", String.class);
    }

    public  int getUserIdFromToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);

        // Obtener el ID de usuario del token
        Integer userIdInt = claims.getBody().get("userId", Integer.class);
        return userIdInt != null ? userIdInt : 0;
    }

}

