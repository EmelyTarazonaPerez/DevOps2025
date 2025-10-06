package com.example.arka.adapters.driving.http.controller;

import com.example.arka.adapters.driven.security.adapter.JwtAdapter;
import com.example.arka.adapters.driving.http.dto.*;
import com.example.arka.adapters.driving.http.mapper.IUserRequestMapper;
import com.example.arka.adapters.driving.http.mapper.IUserResponseMapper;
import com.example.arka.domain.api.IRegisterServicePort;
import com.example.arka.domain.model.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegisterRestController {
    private final IRegisterServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    private final JwtAdapter jwtAdapter;

    @PostMapping("/crear/usuario")
    public ResponseEntity<AuthResponse> employeeRecord (@RequestBody AddUserRequest addUserRequest){
        Usuario Usuario = userRequestMapper.toUser(addUserRequest);
        userServicePort.crearUsuario(Usuario);
        AuthResponse authResponse = AuthResponse.builder().refreshToken(jwtAdapter.getToken(Usuario)).build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("/get/usuario")
    public ResponseEntity<UserResponse> getUserById(@RequestParam() String email){
        return new ResponseEntity<>(userResponseMapper.toUserResponse(userServicePort.buscarUsuarioPorEmail(email)), HttpStatus.OK);
    }

}
