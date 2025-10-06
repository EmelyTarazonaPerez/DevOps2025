package com.example.arka.domain.spi;

import com.example.arka.adapters.driven.security.adapter.JwtAdapter;
import com.example.arka.domain.model.Usuario;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;

public interface IJwtPort {

    public String getToken(Usuario user);

    public String getUsernameFromToken(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver);

    public boolean validateToken(String token);

    public String extractRole(String token);

    public  int getUserIdFromToken(String token);

    public String generateRefreshToken(Usuario user);

    class JwtTokenValidationFilter extends OncePerRequestFilter {
        private final JwtAdapter tokenValidator;

        public JwtTokenValidationFilter(JwtAdapter tokenValidator) {
            this.tokenValidator = tokenValidator;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = getTokenFromRequest(request);

            if (token != null && tokenValidator.validateToken(token)) {
                String role = tokenValidator.extractRole(token);

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, Collections.singletonList(authority));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }

        private String getTokenFromRequest(HttpServletRequest request) {
            final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

            if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
            {
                return authHeader.substring(7);
            }

            return null;
        }
    }
}
