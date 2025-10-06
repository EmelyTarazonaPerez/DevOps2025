package com.example.arka.adapters.driving.http.controller;

import com.example.arka.adapters.driving.http.dto.AuthResponse;
import com.example.arka.adapters.driving.http.dto.LoginRequest;
import com.example.arka.adapters.driving.http.dto.TokenRefreshRequest;
import com.example.arka.domain.model.Usuario;
import com.example.arka.domain.spi.IJwtPort;
import com.example.arka.domain.api.IAuthenticationServicePort;
import com.example.arka.domain.api.IRegisterServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginRestController {
    private  IRegisterServicePort registerServicePort;
    private  IAuthenticationServicePort authenticationServicePort;
    private  IJwtPort jwtService;

    public LoginRestController(IRegisterServicePort registerServicePort,
                               IAuthenticationServicePort authenticationServicePort,
                               @Qualifier("jwtAdapter") IJwtPort jwtService) {
        this.registerServicePort = registerServicePort;
        this.authenticationServicePort = authenticationServicePort;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest) {
        boolean isAuth = authenticationServicePort.authenticate(loginRequest.getGmail(), loginRequest.getPassword());
        AuthResponse authResponse;
        if (isAuth) {
            Usuario user = registerServicePort.buscarUsuarioPorEmail(loginRequest.getGmail());
            String token = jwtService.getToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return new ResponseEntity<>(new AuthResponse(token, refreshToken), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String gmail = jwtService.getUsernameFromToken(refreshToken);

        Usuario user = registerServicePort.buscarUsuarioPorEmail(gmail);
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(gmail)
                .password(user.getPassword())
                .authorities(user.getRole()) // o conviertes tu lista de roles a GrantedAuthority
                .build();
        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            String newAccessToken = jwtService.getToken(user);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
